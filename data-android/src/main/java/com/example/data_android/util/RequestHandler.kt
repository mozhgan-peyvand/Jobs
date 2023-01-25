package com.example.data_android.util

import retrofit2.Response
import javax.inject.Inject

/** A class that handle the request error,
 * if request is successful returns the body and if not extract the error and get message_fa from database. */
class RequestHandler @Inject constructor(){

    /** A function that return body if successful or the Exception.*/
    suspend fun <T> getRequestBodyOrThrow(response: Response<T>): T {
        if (!response.isSuccessful) {
            if (response.code() == 403 && response.headers()["X-Powered-By"] == null) {
//                throw VpnException()
            } else {
//                val serverException = response.errorBody()?.string()?.let { errorString ->
//                    parseHttpException(errorString, response.code())
//                }
//                throw serverException ?: HttpException(response)
            }
        }
        return response.body()!!
    }
}
