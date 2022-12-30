import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.io.ByteArrayOutputStream
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


class CommonModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {

//        val outputText: String = ByteArrayOutputStream().use { outputStream ->
//            project.exec {
//                commandLine("git", "rev-parse", "--short", "HEAD")
//                standardOutput = outputStream
//            }
//            outputStream.toString().trim()
//        }
        //    apply commonPlugin to all project
        project.plugins.apply(BuildPlugins.ANDROID_LIBRARY)
        project.plugins.apply(BuildPlugins.KOTLIN_ANDROID)
        project.plugins.apply(BuildPlugins.MAVEN_PUBLISH)

        //configure the android block
        val androidExtensions = project.extensions.getByName("android")
        if (androidExtensions is BaseExtension) {
            androidExtensions.apply {
                compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
                buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                    }
                }
                defaultConfig {
                    minSdk = BuildAndroidConfig.MIN_SDK_VERSION
                    println(BuildAndroidConfig.MIN_SDK_VERSION)
                    targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
                    versionCode = generateVersionCode()
                    versionName = generateVersionName()
                    vectorDrawables.useSupportLibrary =
                        BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
                    testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
                    consumerProguardFiles("proguard-rules.pro")

                }

                composeOptions.kotlinCompilerExtensionVersion = "1.0.5"
                buildFeatures.compose = true
                flavorDimensions("mode")
                productFlavors {
                    create("mock") {
                        dimension("mode")
//                        buildConfigField(
//                            "String",
//                            "VERSION_NAME",
//                            "\"dev-build${getDate()}-g${outputText}\""
//                        )
//                        val usePublishDependencyInGradle: String by project
//                        buildConfigField(
//                            "String",
//                            "usePublishDependencyInGradle",
//                            "\"${usePublishDependencyInGradle}\""
//                        )
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
                        resValue(
                            "string",
                            "API_URL_FindWork",
                            "\"https://findwork.dev\""
                        )

                    }
                    create("stage") {
                        dimension("mode")
//                        val usePublishDependencyInGradle: String by project
//                        buildConfigField(
//                            "String",
//                            "usePublishDependencyInGradle",
//                            "\"${usePublishDependencyInGradle}\""
//                        )
                    }
                    create("prod") {
                        dimension("mode")
//                        val usePublishDependencyInGradle: String by project
//                        buildConfigField(
//                            "String",
//                            "usePublishDependencyInGradle",
//                            "\"${usePublishDependencyInGradle}\""
//                        )
                    }
                }
            }
            // dependencies common to all projects
            with(project) {
                addCompose()
                dependencies {
                    add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.0")
                    add("implementation", "androidx.core:core-ktx:1.7.0")
                    // testing dependencies
                    add("testImplementation", "junit:junit:4.+")
                    add("androidTestImplementation", "androidx.test.ext:junit:1.1.3")
                    add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.4.0")
                }

            }

        }
    }
}

