package com.example.data.jobs.di


import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class CustomInterceptor @Inject constructor(
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val requestBuilder = request.newBuilder()

        val response = chain.proceed(requestBuilder.build())
        return response
    }
}