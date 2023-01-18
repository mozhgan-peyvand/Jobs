plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.PARCELIZE_Plugins)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
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
    implementation(project(":ui-jobs"))
    implementation(project(":common-ui-view"))
    implementation(project(":ui-user"))
    implementation(project(":base"))
    addCompose()
    addNavigationComponent()
}