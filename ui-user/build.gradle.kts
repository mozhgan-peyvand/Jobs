plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT_PLUGIN)
}

dependencies {

    implementation(project(":base"))
    addCompose()
    addNavigationComponent()
    addHilt()
}