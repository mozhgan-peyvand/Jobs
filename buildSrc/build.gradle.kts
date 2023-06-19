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
//    mavenCentral()
//    google()
    maven {
        url = uri("https://nexus.partdp.ir/repository/part-android/")
        artifactUrls("https://nexus.partdp.ir/repository/part-android/")
        credentials {
            username = "android-user"
            password = "EL2BB+*wkXEaydY=/2>2Kx-tV4CV-%"
        }
    }
}
object PluginsVersions {
    const val GRADLE_ANDROID = "7.3.1"
    const val KOTLIN = "1.8.0"
    const val NAVIGATION = "2.3.0"
    const val HILT_VERSION = "2.45"
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.HILT_VERSION}")
}