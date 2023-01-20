package com.example.base.api


import retrofit2.Response

open class BaseRemoteDataSource {

    protected fun <T> checkApiResult(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null)
                return Resource.Success(body)
        }
        val error = errorParser(response.errorBody()?.string())
        return Resource.Error(
            error
        )
    }
}
