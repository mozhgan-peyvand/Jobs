plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
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
    implementation(project(":feature-jobs:domain-jobs"))
    implementation(project(":common-ui-view"))
    implementation(project(":base"))
    addCompose()
    addNavigationComponent()
    addHilt()
    addCoil()
}