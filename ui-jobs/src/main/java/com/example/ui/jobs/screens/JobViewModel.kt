package com.example.ui.jobs.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.api.Resource
import com.example.ui.jobs.models.toViewJob
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getAllJobRequest: com.example.domain_jobs.usecase.GetAllJobRequest
) : ViewModel() {

    val jobList = mutableStateOf<JobState>(JobState())
    init {
        getAllJobs()
    }
    private fun getAllJobs() {
        viewModelScope.launch {
            getAllJobRequest.invoke().collect { resource ->
                when(resource) {
                    is Resource.Success -> {
                        jobList.value = JobState(
                            data = resource.data?.map { it.toViewJob() }
                        )
                    }
                    is Resource.Error -> {
                        jobList.value = JobState(
                            hasError = true,
                            errorMessage = resource.error.message
                        )
                    }
                    is Resource.Loading -> {
                        jobList.value = JobState(
                            isLoading = true
                        )
                    }
                    else -> {}
                }
            }
        }
    }


}