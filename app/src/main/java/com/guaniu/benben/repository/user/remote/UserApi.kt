package com.guaniu.benben.repository.user.remote

import com.guaniu.benben.utils.Resource
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {
    /**
     * 上传GAID到server端
     */
    @FormUrlEncoded
    @POST("/advertise/v1/gaid")
    fun gaidUpload(@Field("id") gaid : String): Single<Resource<Any>>
}