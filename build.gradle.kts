buildscript {
    repositories {
        maven {
            url = uri("https://nexus.partdp.ir/repository/part-android/")
            artifactUrls("https://nexus.partdp.ir/repository/part-android/")
            credentials {
                username = "android-user"
                password = "EL2BB+*wkXEaydY=/2>2Kx-tV4CV-%"
            }
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}