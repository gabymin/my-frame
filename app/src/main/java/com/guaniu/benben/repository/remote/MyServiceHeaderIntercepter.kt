package com.guaniu.benben.repository.remote

import android.content.Context
import com.guaniu.benben.repository.user.local.UserDao
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class MyServiceHeaderIntercepter @Inject constructor(private val context: Context, private val userDao: UserDao) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
        requestBuilder
            .header("Key", "key")
        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}