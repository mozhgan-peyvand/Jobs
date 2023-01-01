plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
}

dependencies {

    implementation(project(":base"))
    addCompose()
    addNavigationComponent()

}