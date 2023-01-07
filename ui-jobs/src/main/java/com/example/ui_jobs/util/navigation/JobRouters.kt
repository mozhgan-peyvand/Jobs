package com.example.ui_jobs.util.navigation

import com.example.base.routers.AppModuleNames

sealed class JobRouters (val routers: String){
    object JobDetailScreen : JobRouters("${AppModuleNames.MODULE_UI_JOB}://jobDetailScreen")
}