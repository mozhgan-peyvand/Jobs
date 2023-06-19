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
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_11.toString()
                        freeCompilerArgs = listOf("-Xjvm-default=all")

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

                composeOptions.kotlinCompilerExtensionVersion = "1.4.1"
                buildFeatures.compose = true
                flavorDimensions("mode")
                productFlavors {
                    create("mock") {
                        dimension("mode")
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
                    }
                    create("prod") {
                        dimension("mode")
                    }
                }
            }
            // dependencies common to all projects
            with(project) {
                addCompose()
                dependencies {
                    add("implementation", "androidx.core:core-ktx:1.9.0")
                    // testing dependencies
                    add("testImplementation", "junit:junit:4.+")
                    add("androidTestImplementation", "androidx.test.ext:junit:1.1.3")
                    add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.4.0")
                }

            }

        }
    }
}

