package com.ljw.aactestapp.http

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

object RetrofitClientV2 {
    private var instance: retrofit2.Retrofit? = null
    fun getInstance(): retrofit2.Retrofit? {
        if (instance == null) {
            instance = retrofit2.Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(
                    OkHttpClient()
                        .newBuilder()
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return instance
    }

    var iMyService: RetrofitServiceV2 =
        (getInstance() as Retrofit).create(
            RetrofitServiceV2::class.java
        )
}

interface RetrofitServiceV2 {
    @GET("comments")
    fun getTestData(
        @Query("postId") postId: Int
    ): Call<ResponseBody>
}