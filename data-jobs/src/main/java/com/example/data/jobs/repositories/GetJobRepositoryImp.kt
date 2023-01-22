package com.example.data.jobs.repositories

import com.example.base.api.NetworkHandler
import com.example.base.api.Resource
import com.example.domain_jobs.repository.GetJobRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetJobRepositoryImp @Inject constructor(
    private val getJobRemoteDataSource: GetJobRemoteDataSource,
    private val networkHandler: NetworkHandler
): GetJobRepository {

    override suspend fun getAllJobs()= flow {
            if (networkHandler.hasNetworkConnection()) {
                when (val result = getJobRemoteDataSource.getJobList()) {
                    is Resource.Success -> {
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
        if (networkHandler.hasNetworkConnection()){
            when(val result = getJobRemoteDataSource.filterJobList(role,city)){
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