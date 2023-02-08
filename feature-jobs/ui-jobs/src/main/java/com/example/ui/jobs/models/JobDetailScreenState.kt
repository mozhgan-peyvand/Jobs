package com.example.ui.jobs.models

import com.example.base.util.AsyncResult
import com.example.base.util.Uninitialized
import javax.annotation.concurrent.Immutable

@Immutable
data class JobDetailScreenState(
    val jobDetailInfo: AsyncResult<String> = Uninitialized,
    val jobId: String = ""
)