package com.example.ui.jobs.util.navigation

import com.example.base.routers.AppModuleNames

sealed class JobRouters (val routers: String){
// a place for all routers related to job module
    object JobDetailScreen : JobRouters(routers = "${AppModuleNames.MODULE_UI_JOB}://JobDetailScreen")
}