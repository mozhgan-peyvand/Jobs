plugins {
    `kotlin-dsl`
}
gradlePlugin {
    plugins {
        register("module-plugin") {
            id = "module-plugin"
            implementationClass = "CommonModulePlugin"
        }
    }
}
repositories {
    mavenLocal()
    mavenCentral()
    google()
}
object PluginsVersions {
    const val GRADLE_ANDROID = "7.1.2"
    const val KOTLIN = "1.5.31"
    const val NAVIGATION = "2.3.0"
    const val HILT_VERSION = "2.42"
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.HILT_VERSION}")
}