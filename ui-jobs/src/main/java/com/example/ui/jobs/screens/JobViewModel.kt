package com.example.ui.jobs.screens

import com.example.base.JobDto
import com.example.base.util.BaseViewModel
import com.example.domain_jobs.usecase.*
import com.example.ui.jobs.models.JobInfoModel
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
import com.example.ui.jobs.models.toViewJob
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(
    private val insertJobListLocal: InsertJobList,
    private val getAllJob: GetAllJob,
    private val filterJobs: FilterJobList,
    private val getAllLocation: GetAllLocation,
    private val getAllRoles: GetAllRoles,
) : BaseViewModel<JobScreenState, JobScreenUiEvent>(JobScreenState()) {

    var searchResultJobList = listOf<JobDto>()

    init {
        insertJobList()
        onEachAction { action ->
            when (action) {
                is JobScreenUiEvent.FilterJobsList -> filterJobs(action.role, action.city)
                is JobScreenUiEvent.SearchJobsList -> searchJobs(action.searchText ?: "")
            }
        }

        onAsyncResult(
            JobScreenState::allJobList,
            onSuccess = { listJobs ->
                searchResultJobList = listJobs ?: emptyList()
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
        val searchResult = mutableListOf<JobDto>()
        searchResultJobList.map { item ->
            if (
                item.companyName?.contains(searchText) == true ||
                item.location?.contains(searchText) == true ||
                item.role?.contains(searchText) == true
            ) {
                searchResult.add(item)
            }
        }
        setState { copy(searchResultList = searchResult) }
    }

    private fun insertJobList() {
        suspend {
            insertJobListLocal(Unit)
        }.execute(
            onSuccess = {
                getJobList()
                getAllRoles()
                getAllLocations()
            }
        )
    }

    private fun getJobList() {
        getAllJob(Unit)
        getAllJob.flow.execute {
            copy(allJobList = it)
        }
    }

    private fun filterJobs(role: String? = null, city: String? = null) {
        filterJobs(
            FilterJobList.Params(
                role = role,
                city = city
            )
        )
        filterJobs.flow.execute {
            copy(allJobList = it)
        }
    }
}