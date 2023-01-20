plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.HILT_PLUGIN)
}
android{

}

dependencies {
    implementation(project(":domain-jobs"))
    implementation(project(":common-ui-view"))
    implementation(project(":base"))
    addCompose()
    addNavigationComponent()
    addHilt()
    addCoil()
}