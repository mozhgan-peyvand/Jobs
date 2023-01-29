plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.PARCELIZE_Plugins)
    id(BuildPlugins.HILT_PLUGIN)
}

dependencies {
    addHilt()
    addRetrofit()
    addMoshi()
    addKotshi()
    addCoroutine()
    addRoom()
}