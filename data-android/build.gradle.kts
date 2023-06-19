plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.PARCELIZE_Plugins)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT_PLUGIN)
}


dependencies {
    implementation(project(":feature-jobs:data-jobs"))
    implementation(project(":feature-user:data-user"))
    implementation(project(":base"))
    addHilt()
    addRetrofit()
    addMoshi()
    addCoroutine()
    addRoom()
}