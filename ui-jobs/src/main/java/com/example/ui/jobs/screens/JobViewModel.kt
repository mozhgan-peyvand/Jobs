package com.example.ui.jobs.screens

import com.example.base.util.BaseViewModel
import com.example.domain_jobs.usecase.FilterJobList
import com.example.domain_jobs.usecase.GetAllJobRequest
import com.example.domain_jobs.usecase.GetAllLocation
import com.example.domain_jobs.usecase.GetAllRoles
import com.example.ui.jobs.models.JobInfoModel
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

    var searchResultJobList = listOf<JobInfoModel>()

    init {
        getAllJobs()
        onEachAction { action ->
            when (action) {
                JobScreenUiEvent.ShowAllJobList -> getAllJobs()
                is JobScreenUiEvent.FilterJobsList -> filterJobs(action.role, action.city)
                is JobScreenUiEvent.SearchJobsList -> searchJobs(action.searchText ?: "")
            }
        }

        onAsyncResult(
            JobScreenState::allJobList,
            onSuccess = { listJobs ->
                getAllRoles()
                getAllLocations()
                searchResultJobList = listJobs?.map { it.toViewJob() } ?: emptyList()
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

    private fun searchJobs(searchText: String) {
        val searchResult = mutableListOf<JobInfoModel>()
        suspend {
            searchResultJobList.map { item ->
                if (
                    item.companyName?.contains(searchText) == true ||
                    item.locationCompany?.contains(searchText) == true ||
                    item.role?.contains(searchText) == true
                ) {
                    searchResult.add(item)
                }
            }
        }.execute {
            copy(searchResultList = searchResult)
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
        }.execute {
            copy(allJobList = it)
        }

    }
}