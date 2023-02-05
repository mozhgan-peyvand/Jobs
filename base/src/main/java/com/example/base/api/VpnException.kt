package com.example.base.api

import java.io.IOException


class VpnException : IOException()
fun Throwable.getErrorMessage(): Any {
    return when (this) {
        is ServerException -> {
            if (code == 404 && metaCode == "functionNotFound") {
            } else {
                serverMessage.orEmpty()
            }
        }
        is VpnException -> "error vpn exception"
        is IOException -> "error not connect"
        else -> "error unspect"
    }
}