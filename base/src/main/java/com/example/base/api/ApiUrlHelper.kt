package com.example.base.api

object ApiUrlHelper {

    init {
        update()
    }

    lateinit var API_URL: String

    private fun update() {
        API_URL = "https://findwork.dev/api/jobs/"
    }
}
