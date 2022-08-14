package com.westig.frame.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.logger.Logger
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException


fun <T> Single<T>.background() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <T> Observable<T>.background() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())!!
fun <T> Maybe<T>.background() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


fun <T : Resource<*>> Single<T>.request(observer: (T) -> Unit): Disposable {
    return request(observer, null)
}

fun <T : Resource<*>> Single<T>.request(observer: (T) -> Unit, failCustomer: ((T) -> Unit)?): Disposable {
    return request(false, null, observer, failCustomer)
}

fun <T> Single<T>.request(host: BackgroundTaskHost<Resource<*>>, successCustomer: (T) -> Unit): Disposable {
    return request(false, host, successCustomer, null)
}

fun <T> Single<T>.request(host: BackgroundTaskHost<Resource<*>>, successCustomer: (T) -> Unit, failCustomer: ((T) -> Unit)): Disposable {
    return request(false, host, successCustomer, failCustomer)
}

@Suppress("UNCHECKED_CAST")
fun <T> Single<T>.request(sync: Boolean, host: BackgroundTaskHost<Resource<*>>?, successCustomer: (T) -> Unit, failCustomer: ((T) -> Unit)?): Disposable {

    host?.backgroundTaskStart()

    var that = this
    if (sync) {
        that = this.background()
    }

    return that.subscribe({
        host?.backgroundTaskStop(null, null)
        successCustomer(it)

    }) { throwable ->

        var result: T? = null
        if (throwable is HttpException ) {
            //&& throwable.code() != 401 401的错误类型太多了，就不拦截了
            // code  401 解析异常，暂时处理， https://gitlab.qiteck.net:10002/mage-app/issue/issues/331#note_5759
            try {
                val turnsType = object : TypeToken<Resource<Any>>() {}.type

                result = Gson().fromJson<Resource<Any>>(
                        throwable.response()!!.errorBody()!!.string(),
                        turnsType
                ) as T

            } catch (ex: Exception) {
                Logger.e(ex, "解析HTTP响应消息时出错了")
            }
        } else {
            Logger.e(throwable, "发生未知错误");
        }



        if (result == null) {
            result = Resource.fail<Any>("unknown_exception", throwable.toString()) as T
        }

        host?.backgroundTaskStop(result as Resource<*>, throwable)
        Logger.i("关闭processing对话框")
        failCustomer?.let {
            try {
                it(result!!)
            } catch (ex: Exception) {
                Logger.e(ex, "failCustomer error")
            }
        }

    }
}
