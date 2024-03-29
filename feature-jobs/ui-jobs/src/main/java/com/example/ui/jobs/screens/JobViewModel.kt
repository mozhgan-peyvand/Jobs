package com.example.ui.jobs.screens

import androidx.lifecycle.DefaultLifecycleObserver
import com.example.base.models.JobDto
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
    private val getJobList: GetJobList
) : BaseViewModel<JobScreenState, JobScreenUiEvent>(JobScreenState()), DefaultLifecycleObserver {

    private var searchResultJobList = listOf<JobDto>()
    var currentPage = 1

    init {
        insertJobList()

        onEachAction { action ->
            when (action) {
                JobScreenUiEvent.ShowAllJobList -> insertJobList()
                JobScreenUiEvent.RefreshJobList -> refresh()
                JobScreenUiEvent.ShowNextPage -> getNextPage()
                is JobScreenUiEvent.SearchJobsList -> searchJobs(action.searchText ?: "")
                else -> throw IllegalArgumentException("unknown action: $action")
            }
        }

        onAsyncResult(
            JobScreenState::JobList,
            onSuccess = { listJobs ->
                searchResultJobList = listJobs ?: emptyList()
            },
            onFail = {
                setState { copy(JobList = Fail(it)) }
            }
        )
        onAsyncResult(
            JobScreenState::insertJobList,
            onSuccess = {
                getJobList()
            },
            onFail = {
                setState { copy(insertJobList = Fail(it)) }
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
        setState { copy(searchJobList = searchResult) }
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
        getJobList(Unit)
        getJobList.flow.execute(
            reducer = {
                copy(JobList = it)
            },
            page = currentPage
        )
    }
}