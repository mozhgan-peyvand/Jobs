package com.example.base

interface BaseEffect {
    object ShowSnackBar : BaseEffect
    data class NavigateTo(val route: String) : BaseEffect
}
