package com.example.ui.jobs.models


sealed class JobScreenUiEvent {
    object ShowAllJobList : JobScreenUiEvent()
    object ShowAllLocation : JobScreenUiEvent()
    object ShowAllRole : JobScreenUiEvent()
    data class FilterJobsList(val role: String?,val city: String?) : JobScreenUiEvent()
}