package com.example.ui.jobs.screens

import com.example.base.JobDto
import com.example.base.util.BaseViewModel
import com.example.base.util.Fail
import com.example.domain_jobs.usecase.*
import com.example.ui.jobs.models.JobScreenState
import com.example.ui.jobs.models.JobScreenUiEvent
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

    private var searchResultJobList = listOf<JobDto>()
    var currentPage = 1

    init {
        insertJobList()

        onEachAction { action ->
            when (action) {
                JobScreenUiEvent.ShowAllJobList -> insertJobList()
                JobScreenUiEvent.RefreshJobList -> refresh()
                JobScreenUiEvent.ShowNextPage -> getNextPage()
                is JobScreenUiEvent.FilterJobsList -> filterJobs(action.role, action.city)
                is JobScreenUiEvent.SearchJobsList -> searchJobs(action.searchText ?: "")
                else -> throw IllegalArgumentException("unknown action: $action")
            }
        }

        onAsyncResult(
            JobScreenState::allJobList,
            onSuccess = { listJobs ->
                searchResultJobList = listJobs ?: emptyList()
            }
        )
        onAsyncResult(
            JobScreenState::insertJobList,
            onSuccess = {
                getJobList()
                getAllRoles()
                getAllLocations()
            }
        )

    }

    fun getNextPage() {
        currentPage++
        insertJobList()
    }

    fun refresh() {
        currentPage = 1
        insertJobList()
    }

    private fun getAllLocations() {
        getAllLocation(Unit)
        getAllLocation.flow.execute(
            reducer = { copy(allLocationList = it) }
        )
    }

    private fun getAllRoles() {
        getAllRoles(Unit)
        getAllRoles.flow.execute(
            reducer = {
                copy(allRoleList = it)
            }
        )

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
            insertJobListLocal(InsertJobList.Param(currentPage))
        }.execute(
            reducer = { copy(insertJobList = it) },
            page = currentPage
        )

    }

    private fun getJobList() {
        getAllJob(Unit)
        getAllJob.flow.execute(
            reducer = {
                copy(allJobList = it)
            },
            page = currentPage
        )
    }

    private fun filterJobs(role: String? = null, city: String? = null) {
        filterJobs(
            FilterJobList.Params(
                role = role,
                city = city
            )
        )
        filterJobs.flow.execute(reducer = {
            copy(allJobList = it)
        }
        )
    }
}