import java.io.ByteArrayOutputStream

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.ORG_JETBRAINS_KOTLIN_ANDROID)
}
fun getGitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}
android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        multiDexEnabled = true
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        versionCode = generateVersionCode()
        versionName = generateVersionName()
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions.add("mode")
    productFlavors {
        create("dev") {
            dimension = "mode"
            applicationIdSuffix = ".dev"
            versionName = "dev-build${getDate()}-g${getGitHash()}"
            // The following configuration limits the "dev" flavor to using
            // English string resources and xxhdpi screen-density resources.
            resourceConfigurations.addAll(listOf("en", "xxhdpi"))
            // Disable PNG crunching
            aaptOptions.cruncherEnabled = false
            // Disable Split apk in development
            splits {
                abi {
                    isEnable = false
                }
                density {
                    isEnable = false
                }
            }
        }
        create("stage") {
            dimension = "mode"
            applicationIdSuffix = ".stage"
//            signingConfig = signingConfigs.getByName("release")
        }
        create("prod") {
            dimension = "mode"
            applicationIdSuffix = ".prod"
//            signingConfig = signingConfigs.getByName("release")
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }

}

dependencies {
    addCompose()
}