plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
}

dependencies {
    implementation(project(":common-ui-view"))
    implementation(project(":base"))
    addCompose()
    addNavigationComponent()
}