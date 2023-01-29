package com.example.base.util

interface BaseEffect {
    object ShowSnackBar : BaseEffect
    data class NavigateTo(val route: String) : BaseEffect
}
