package com.example.data.jobs.models

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class PublicResponse<out T> (
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>? = null
)
