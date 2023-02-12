package com.example.ui.jobs.models


sealed class JobScreenUiEvent {
    object ShowAllJobList : JobScreenUiEvent()
    object ShowNextPage : JobScreenUiEvent()
    object RefreshJobList : JobScreenUiEvent()
    data class SearchJobsList(val searchText: String? = "") : JobScreenUiEvent()
}