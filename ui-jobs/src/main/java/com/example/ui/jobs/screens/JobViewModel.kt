package com.example.ui.jobs.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.base.BaseViewModel
import com.example.domain_jobs.usecase.FilterJobList
import com.example.domain_jobs.usecase.GetAllJobRequest
import com.example.domain_jobs.usecase.GetAllLocation
import com.example.domain_jobs.usecase.GetAllRoles
import com.example.ui.jobs.models.JobInfoModel
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val getAllJobRequest: GetAllJobRequest,
    private val filterJobsRequest: FilterJobList,
    private val getAllLocation: GetAllLocation,
    private val getAllRoles: GetAllRoles,
) : BaseViewModel<JobScreenState, JobScreenUiEvent>(JobScreenState()) {

    val filterResultList = mutableStateListOf<String>("","")
    val searchResultList = mutableStateListOf<JobInfoModel>()
    val closeSearch = mutableStateOf(true)
    var searchText = mutableStateOf("")

    init {
        getAllJobs()
        onEachAction { action ->
            when (action) {
                JobScreenUiEvent.ShowAllJobList -> getAllJobs()
                is JobScreenUiEvent.FilterJobsList -> filterJobs(action.role, action.city)
                else -> throw IllegalArgumentException("unknown action: $action")
            }
        }

        onAsyncResult(
            JobScreenState::allJobList,
            onSuccess = {
                    getAllRoles()
                    getAllLocations()
            }
        )
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