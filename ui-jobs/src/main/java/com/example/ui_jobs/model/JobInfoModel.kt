package com.example.ui_jobs.model

data class JobInfoModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes : Int,

){
    companion object {
        var ID = 0
    }
}