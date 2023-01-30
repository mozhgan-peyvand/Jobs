package com.example.ui.jobs.models


sealed class JobScreenUiEvent {
    object ShowAllJobList : JobScreenUiEvent()
    data class FilterJobsList(val role: String?,val city: String?) : JobScreenUiEvent()
    data class SearchJobsList(val searchText: String? = "") : JobScreenUiEvent()
}