package com.example.common_ui_view

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