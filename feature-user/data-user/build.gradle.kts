plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.PARCELIZE_Plugins)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT_PLUGIN)
}


dependencies {
    implementation(project(":base"))
    implementation(project(":feature-user:domain-user"))
    addHilt()
    addMoshi()
    addCoroutine()
    addRoom()
}