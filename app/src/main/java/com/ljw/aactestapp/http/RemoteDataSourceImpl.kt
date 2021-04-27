package com.ljw.aactestapp.http

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl : RemoteDataSource {
    override fun getTestData(
        id: Int,
        onResponse: (Response<ResponseBody>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        RetrofitClientV2.iMyService.getTestData(id)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    onResponse(response)
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    onFailure(t)
                }
            })
    }
}