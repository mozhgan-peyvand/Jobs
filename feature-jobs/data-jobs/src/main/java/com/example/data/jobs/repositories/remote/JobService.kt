package com.example.data.jobs.repositories.remote

import com.example.data.jobs.models.JobResponse
import com.example.base.api.PublicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

interface JobService {
    @GET
    suspend fun getAllJobs(
        @Url url: String,
        @Header("Authorization") authorization : String = "Token b3fd31cc427e369c9a0c1b0c7728144e956a6e96",
        @Query("page") page: Int
    ): Response<PublicResponse<JobResponse>>

}