plugins {
    id(BuildPlugins.MODULE_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
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


}