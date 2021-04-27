package com.ljw.aactestapp.http

import okhttp3.ResponseBody
import retrofit2.Response

interface RemoteDataSource {
    fun getTestData(
        id: Int,
        onResponse: (Response<ResponseBody>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}