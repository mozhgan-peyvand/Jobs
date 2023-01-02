package com.example.base.routers


sealed class AppRouters(val routers: String) {
    object AppGraph : AppRouters(routers = "${AppModuleNames.MODULE_BUILDER}://builderGraph")
    object JobGraph : AppRouters(routers = "${AppModuleNames.MODULE_UI_JOB}://jobGraph")
    object UserGraph : AppRouters(routers = "${AppModuleNames.MODULE_UI_USER}://userGraph")
    object JobScreen : AppRouters(routers = "${AppModuleNames.MODULE_UI_JOB}://jobScreen")
    object UserScreen : AppRouters(routers = "${AppModuleNames.MODULE_UI_USER}://userScreen")
    object FavoriteGraph :
        AppRouters(routers = "${AppModuleNames.MODULE_UI_FAVORITE}://favoriteGraph")

    object FavoriteScreen :
        AppRouters(routers = "${AppModuleNames.MODULE_UI_FAVORITE}://favoriteScreen")
}