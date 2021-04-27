package com.ljw.aactestapp.http

import okhttp3.ResponseBody
import retrofit2.Response

class Repository {
    val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()

    fun getTestData(
        id: Int,
        onResponse: (Response<ResponseBody>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        retrofitRemoteDataSource.getTestData(id, onResponse, onFailure)
    }
}