package com.guaniu.benben.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import com.guaniu.benben.utils.BackgroundTaskHost
import com.guaniu.benben.utils.Event
import com.guaniu.benben.utils.Resource
import com.guaniu.benben.utils.RxJavaSingleEmptyException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel(), BackgroundTaskHost<Resource<*>> {
    protected val compositeDisposable = CompositeDisposable()

    private val mapDisposable = mutableMapOf<String, Disposable>()

    val resourceRequestProgressing = MutableLiveData<Boolean>()
    val failedResourceEvent = MutableLiveData<Event<Resource<*>>>()

    fun disposeAfterClear(block: () -> Disposable?) {
        val disposable = block()
        disposable?.let {
            compositeDisposable.add(it)
        }
    }


    /**
     * @param key 根据 KEY key一样的不要重复监听
     */
    fun disposeAfterUpdate(key: String, block: () -> Disposable?) {
        val disposable = block()
        disposable?.let {

            if (mapDisposable[key] != null) {
                mapDisposable[key]?.dispose()
            }
            mapDisposable.put(key, it)
        }
    }


    override fun onCleared() {
        Logger.i("BaseViewModel onCleared()+"+this.javaClass +compositeDisposable.size() +" "+mapDisposable.values.size)
        compositeDisposable.dispose()

        for (d in mapDisposable.values) {
            d.dispose()
        }
        super.onCleared()
    }

    fun handleFailedResource(resource: Resource<*>) {
        failedResourceEvent.postValue(Event(resource))
    }

    override fun backgroundTaskStart() {
        resourceRequestProgressing.postValue(true)
    }

    override fun backgroundTaskStop(result: Resource<*>?, throwable: Throwable?) {
        resourceRequestProgressing.postValue(false)

        throwable?.let {

            if (it is ConnectException
                || it is SocketTimeoutException
                || it is UnknownHostException
            ) {
                val resource: Resource<String> = Resource.fail(code = "connect_exception", message = "connect exception")
                failedResourceEvent.postValue(Event(resource))
                return
            } else if (it !is RxJavaSingleEmptyException) {
                result?.let {
                    handleFailedResource(result)
                    return
                }
                val resource: Resource<String> = Resource.fail(code = "unknown_exception", message = "unknown exception")
                failedResourceEvent.postValue(Event(resource))
                return
            }
            Logger.w("未处理异常: $throwable")

        }

    }
}