package com.example.base.api

import android.provider.Settings.System.getString
import androidx.core.content.res.TypedArrayUtils.getString
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

/** A class that handle the request error,
 * if request is successful returns the body and if not extract the error and get message_fa from database. */
class RequestHandler @Inject constructor(){

    /** A function that return body if successful or the Exception.*/
    suspend fun <T> getRequestBodyOrThrow(response: Response<T>): T {
        if (!response.isSuccessful) {
            if (response.code() == 403 && response.headers()["X-Powered-By"] == null) {
                throw VpnException()
            } else {
                val serverException = response.errorBody()?.string()?.let { errorString ->
                    parseHttpException(errorString, response.code())
                }
                throw HttpException(response)
            }
        }
        return response.body()!!
    }
}
/** A function that parse [ServerException] from json error and get message_fa from database. */
private suspend fun parseHttpException(errorString: String, errorCode: Int): ServerException? {
    return try {

        val errorBodyObject = JSONObject(errorString)
        val metaData = errorBodyObject.getJSONObject("meta")
        val errorData = errorBodyObject.getJSONObject("data")
        var metaCode: String? = null

        if (!metaData.isNull("code")) {
            metaCode = metaData.getString("code")
        }

        var errorMessage: String? = errorData.getErrorMessageFromErrorData()


        ServerException(errorMessage, errorCode, metaCode)
    } catch (e: Exception) {
        null
    }
}

private fun JSONObject.getErrorMessageFromErrorData(): String? {
    var message: String? = null
    if (get("message") is String) {
        message = getString("message")
    } else if (get("message") is JSONObject) {
        message = getJSONObject("message").getString("fa")
    }
    return message
}