package com.example.ui.jobs.screens

import com.example.base.BaseViewModel
import com.example.base.Success
import com.example.base.api.Resource
import com.example.domain_jobs.model.GetJob
import com.example.domain_jobs.usecase.FilterJobList
import com.example.domain_jobs.usecase.GetAllJobRequest
import com.example.domain_jobs.usecase.GetAllLocation
import com.example.domain_jobs.usecase.GetAllRoles
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import com.example.ui.jobs.models.toViewJob
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
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
            getAllJobRequest(Unit)
        }.execute {
           if (it.invoke()?.isNotEmpty() == true){
               getAllRoles()
               getAllLocations()
           }
           copy(allJobList = it)
       }
    }

    private fun filterJobs(role: String? = null, city: String? = null) {
        suspend {
            val params = FilterJobList.Params(
                role = role,
                city = city
            )
            filterJobsRequest(params)
        }.execute{
            copy(allJobList = it)
        }

    }
}