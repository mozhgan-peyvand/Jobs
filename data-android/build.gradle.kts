plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.PARCELIZE_Plugins)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT_PLUGIN)
}


dependencies {
    implementation(project(":data-jobs"))
    implementation(project(":base"))
    addHilt()
    addRetrofit()
    addMoshi()
    addKotshi()
    addCoroutine()
    addRoom()
}