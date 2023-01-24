package com.example.ui.jobs.screens

import com.example.base.BaseViewModel
import com.example.base.Success
import com.example.base.api.Resource
import com.example.domain_jobs.usecase.FilterJobList
import com.example.domain_jobs.usecase.GetAllJobRequest
import com.example.domain_jobs.usecase.GetAllLocation
import com.example.domain_jobs.usecase.GetAllRoles
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import com.example.ui.jobs.models.toViewJob
import dagger.hilt.android.lifecycle.HiltViewModel
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
                is JobScreenUiEvent.FilterJobsList -> filterJobs(action.role, action.city)
                else -> throw IllegalArgumentException("unknown action: $action")
            }
        }

    }


    private fun getAllLocations() {
        getAllLocation(Unit)
        getAllLocation.flow.execute {
            copy(allLocationList = it)
        }
    }

    private fun getAllRoles() {
        getAllRoles(Unit)
        getAllRoles.flow.execute {
            copy(allRoleList = it)
        }
    }


    private fun getAllJobs() {
        suspend {
            getAllJobRequest.invoke()
        }.execute(
            onSuccess = { jobList ->
                jobList.collect{ resource ->
                    if (resource is Resource.Success)
                        setState { copy(allJobList = Success(resource.data?.map { it.toViewJob() })) }
                    getAllLocations()
                    getAllRoles()
                }
            }
        )
    }

    private fun filterJobs(role: String? = null, city: String? = null) {
        suspend {
            filterJobsRequest.invoke(role = role, city = city)
        }.execute(
            onSuccess = { jobList ->
                jobList.collect { resource ->
                    if (resource is Resource.Success)
                        setState { copy(allJobList = Success(resource.data?.map { it.toViewJob() })) }
                }
            }
        )
    }


}