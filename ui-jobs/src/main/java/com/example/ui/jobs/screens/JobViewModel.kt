package com.example.ui.jobs.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.api.Resource
import com.example.domain_jobs.usecase.*
import com.example.ui.jobs.models.toViewJob
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getAllJobRequest : GetAllJobRequest,
    private val filterJobsRequest : FilterJobList,
    private val getAllLocation: GetAllLocation,
    private val getAllRoles: GetAllRoles,
    private val saveAllJobs: SaveAllJobs
) : ViewModel() {

    private var _jobList = mutableStateOf(JobScreenState())
    val jobList: State<JobScreenState> = _jobList

    init {
        getAllJobs()
    }
    val locationList = getAllLocation.invoke()

    val rolesList = getAllRoles.invoke()

    private fun getAllJobs() {
        viewModelScope.launch {
            getAllJobRequest.invoke().collect { resource ->
                when(resource) {
                    is Resource.Success -> {
                        _jobList.value = JobScreenState(
                            data = resource.data?.map { it.toViewJob()},
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _jobList.value = JobScreenState(
                            hasError = true,
                            errorMessage = resource.error.message
                        )
                    }
                    is Resource.Loading -> {
                        _jobList.value = JobScreenState(
                            isLoading = true
                        )
                    }
                    else -> {}
                }
            }
        }
    }

    fun filterJobs(role : String? = null,city: String? = null){
        viewModelScope.launch {
            filterJobsRequest.invoke(role = role,city = city).collect { resource ->
                when(resource){
                    is Resource.Success -> {
                         _jobList.value = JobScreenState(
                             data = resource.data?.map { it.toViewJob() },
                             isLoading =  false
                         )
                    }
                    is Resource.Error -> {
                        _jobList.value = JobScreenState(
                            hasError = true,
                            errorMessage =  resource.error.message
                        )
                    }
                    is Resource.Loading -> {
                        _jobList.value = JobScreenState(
                            isLoading = true
                        )
                    }
                    else -> {

                    }
                }

            }
        }
    }


}