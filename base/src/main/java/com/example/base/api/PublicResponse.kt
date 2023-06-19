package com.example.base.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PublicResponse<out T> (
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>? = null
)
