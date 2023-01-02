plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.PARCELIZE_Plugins)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}


dependencies {

    implementation(project(":ui-jobs"))
    implementation(project(":ui-user"))
    implementation(project(":base"))
    addCompose()
    addNavigationComponent()
}