package com.example.data.jobs.repositories

import com.example.base.api.NetworkHandler
import com.example.base.api.Resource
import com.example.domain_jobs.model.GetJob
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetJobRepositoryImp @Inject constructor(
    private val getJobRemoteDataSource: GetJobRemoteDataSource,
    private val getJobLocalDataSource: GetJobLocalDataSource,
    private val networkHandler: NetworkHandler
) : GetJobRepository {
    override fun getAllRoles(): Flow<List<String>> {
        return getJobLocalDataSource.getRolesList()
    }

    override fun getAllLocation(): Flow<List<String>> {
        return getJobLocalDataSource.getLocationList()
    }

    override suspend fun getAllJobs()= flow {
            if (networkHandler.hasNetworkConnection()) {
                when (val result = getJobRemoteDataSource.getJobList()) {
                    is Resource.Success -> {
                        getJobLocalDataSource.insertArticleList(result.data.results?.map { it.toJobDto() } ?: emptyList())
                        emit(Resource.Success(
                            result.data.results?.map { it.toGetJob() }
                        ))
                    }
                    is Resource.Error -> emit(Resource.Error(result.error))
                    else -> {
                       emit( Resource.Error(IOException()))
                    }
                }
            } else emit(Resource.Error(IOException()))
        }

    override suspend fun filterJobsList(role: String?, city: String?) = flow {
        if (networkHandler.hasNetworkConnection()) {
            when (val result = getJobRemoteDataSource.filterJobList(role, city)) {
                is Resource.Success -> {
                    emit(
                        Resource.Success(
                            result.data.results?.map { it.toGetJob() }
                        )
                    )
                }
                is Resource.Error -> emit(
                    Resource.Error(result.error)
                )
                else -> {
                    emit(Resource.Error(IOException()))
                }
            }
        } else emit(Resource.Error(IOException()))
    }
}