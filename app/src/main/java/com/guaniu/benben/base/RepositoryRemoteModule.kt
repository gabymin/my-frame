package com.guaniu.benben.base

import android.app.Application
import com.guaniu.benben.BuildConfig
import com.guaniu.benben.repository.remote.MyServiceHeaderIntercepter
import com.guaniu.benben.repository.user.remote.UserApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RepositoryRemoteModule {
    @Module
    companion object {


        @JvmStatic
        @Provides
        @Singleton
        fun provideHttpCache(application: Application): Cache {
            val cacheSize = 10 * 1024 * 1024
            return Cache(application.cacheDir, cacheSize.toLong())
        }


        @JvmStatic
        @Provides
        @Singleton
        fun provideAppServiceOkhttpClient(
            cache: Cache,
            headerInterceptor: MyServiceHeaderIntercepter
        ): OkHttpClient {
            val client = OkHttpClient().newBuilder()
            client.addInterceptor(headerInterceptor)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(30, TimeUnit.SECONDS)
                // 写入超时时间设置
                .writeTimeout(30, TimeUnit.SECONDS)

            //debug版本显示log
            if (BuildConfig.SAVE_LOG) {
                var logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }

            client.cache(cache)
            return client.build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideAppServiceRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder().baseUrl(BuildConfig.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideUserApi(retrofit: Retrofit): UserApi {
            return retrofit.create(UserApi::class.java)
        }

    }
}