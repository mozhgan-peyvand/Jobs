package com.example.data.jobs.repositories

import com.example.data.jobs.models.GetJobResponse
import com.example.data.jobs.models.PublicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface JobService {
    @GET
    suspend fun getAllJobs(
        @Url url: String,
        @Header("Authorization") authorization : String = "Token b3fd31cc427e369c9a0c1b0c7728144e956a6e96"
    ): Response<PublicResponse<GetJobResponse>>

    @GET
    suspend fun searchJobs(
        @Url url: String,
        @Header("Authorization") authorization: String = "Token b3fd31cc427e369c9a0c1b0c7728144e956a6e96",
        @Query("location") location: String?,
        @Query("search") search: String?,
        @Query("sort_by") sortBy: String = "relevance"
    ): Response<PublicResponse<GetJobResponse>>
}