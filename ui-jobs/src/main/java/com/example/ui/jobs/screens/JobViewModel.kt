package com.example.ui.jobs.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.AsyncResult
import com.example.base.BaseViewModel
import com.example.base.Success
import com.example.base.api.Resource
import com.example.domain_jobs.usecase.*
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import com.example.ui.jobs.models.toViewJob
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getAllJobRequest: GetAllJobRequest,
    private val filterJobsRequest: FilterJobList,
    private val getAllLocation: GetAllLocation,
    private val getAllRoles: GetAllRoles,
) : BaseViewModel<JobScreenState, JobScreenUiEvent>(JobScreenState()) {

    init {
        getAllJobs()

        onEachAction { action ->
            when (action) {
                is JobScreenUiEvent.FilterJobsList -> filterJobs(action.role,action.city)
                else -> throw IllegalArgumentException("unknown action: $action")
            }
        }

    }


    private fun getAllLocations(){
        suspend {
            getAllLocation.invoke()
        }.execute(
            onSuccess = { locationList ->
                locationList.collect{
                    setState { copy(allLocationList =it) }
                }

            }
        )
    }

    private fun getAllRoles() {
        suspend {
            getAllRoles.invoke()
        }.execute(
            onSuccess = { RoleList ->
                RoleList.collect{ resource ->
                        setState { copy(allRoleList = resource) }
                }
            }
        )
    }


    private fun getAllJobs() {
        suspend {
            getAllJobRequest.invoke()
        }.execute(
            onSuccess = { jobList ->
                jobList.collect { resource ->
                    if (resource is Resource.Success)
                    setState { copy(allJobList = Success(resource.data?.map { it.toViewJob() } )) }
                    getAllLocations()
                    getAllRoles()
                }
            }
        )
    }

    private fun filterJobs(role: String? = null, city: String? = null) {
        suspend {
            filterJobsRequest.invoke(role = role,city = city)
        }.execute(
            onSuccess = { jobList ->
                jobList.collect{ resource ->
                    if (resource is Resource.Success)
                        setState { copy(allJobList = Success(resource.data?.map { it.toViewJob() } )) }
                }
            }
        )
    }


}