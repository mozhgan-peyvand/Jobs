package com.example.ui.jobs.models

sealed class JobDetailScreenUiEvent {
    data class GetJobDetailInfo(val jobId: String) : JobDetailScreenUiEvent()

}