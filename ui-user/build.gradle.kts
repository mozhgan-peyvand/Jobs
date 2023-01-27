plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT_PLUGIN)
}

dependencies {

    implementation(project(":base"))
    implementation(project(":common-ui-view"))
    addCompose()
    addNavigationComponent()
    addHilt()
}