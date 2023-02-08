package com.example.ui.jobs.screens

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.SavedStateHandle
import com.example.base.util.BaseViewModel
import com.example.domain_jobs.usecase.GetJobDetailInfo
import com.example.ui.jobs.models.JobDetailScreenState
import com.example.ui.jobs.models.JobDetailScreenUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobDetailViewModel @Inject constructor(
    private val getJobDetailInfo: GetJobDetailInfo,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<JobDetailScreenState, JobDetailScreenUiEvent>(JobDetailScreenState()),
    DefaultLifecycleObserver {

    init {
        getJobDetail()

        onEachAction { action ->
            when (action) {
                is JobDetailScreenUiEvent.GetJobDetailInfo -> getJobDetail()
            }
        }
    }

    private fun getJobDetail() {
        getJobDetailInfo(GetJobDetailInfo.Param(savedStateHandle.get<String>(KEY_ARTICLE_ID) ?: ""))
        getJobDetailInfo.flow.execute(
            reducer = { copy(jobDetailInfo = it) }
        )

    }

}