plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.PARCELIZE_Plugins)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
    id(BuildPlugins.HILT_PLUGIN)
}

android{
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
        kotlinOptions {
            jvmTarget = "11"

            // For creation of default methods in interfaces
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }
}
dependencies {
    implementation(project(":feature-jobs:ui-jobs"))
    implementation(project(":common-ui-view"))
    implementation(project(":feature-user:ui-user"))
    implementation(project(":base"))
    implementation(project(":feature-jobs:data-jobs"))
    implementation(project(":feature-user:data-user"))
    implementation(project(":feature-jobs:domain-jobs"))
    implementation(project(":feature-user:domain-user"))
    implementation(project(":data-android"))
    addCompose()
    addNavigationComponent()
    addHilt()
    addCoroutine()
    addRoom()

}