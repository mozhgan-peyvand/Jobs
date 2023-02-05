package com.example.base.api

data class ServerException(
    val serverMessage: String?,
    val code: Int,
    val metaCode: String?
) :
    Exception("Server error $code ($metaCode) $serverMessage")
