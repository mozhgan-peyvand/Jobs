import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.addCompose() {
    dependencies {
        val composeVersion = "1.3.0"
        add("implementation", "androidx.compose.ui:ui:$composeVersion")
        add("implementation", "androidx.compose.material:material:1.4.0-alpha03")
        add("implementation", "androidx.compose.material:material-icons-extended:1.4.3")
        add("implementation", "androidx.compose.ui:ui-tooling-preview:$composeVersion")
        add("debugImplementation", "androidx.compose.ui:ui-tooling:$composeVersion")
        add("implementation", "androidx.activity:activity-compose:1.5.1")
        add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
        add("implementation", "com.google.android.material:material:1.2.1")
        add("implementation", "androidx.navigation:navigation-compose:2.5.3")
        add("implementation", "com.google.accompanist:accompanist-navigation-animation:0.27.0")
        add("implementation", "com.google.accompanist:accompanist-swiperefresh:0.27.0")
        add( "implementation" ,"com.google.accompanist:accompanist-systemuicontroller:0.27.0")
    }
}

fun Project.addNavigationComponent() {
    dependencies {
        add("implementation", "androidx.navigation:navigation-fragment-ktx:2.3.1")
        add("implementation", "androidx.navigation:navigation-ui-ktx:2.3.1")
        add("implementation", "androidx.navigation:navigation-common-ktx:2.3.1")
        add("implementation", "androidx.navigation:navigation-runtime-ktx:2.3.1")
        add(
            "androidTestImplementation",
            "android.arch.navigation:navigation-testing-ktx:1.0.0-alpha06"
        )
    }
}

fun Project.addHilt() {
    dependencies {
        val hiltVersion = "2.45"
        add("implementation", "com.google.dagger:hilt-android:${hiltVersion}")
        add("kapt", "com.google.dagger:hilt-android-compiler:${hiltVersion}")
        add("implementation", "androidx.hilt:hilt-navigation-compose:1.0.0")
        add("implementation", "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    }
}

fun Project.addRetrofit() {
    dependencies {
        add("implementation", "com.squareup.retrofit2:retrofit:2.9.0")
        add("implementation", "com.squareup.retrofit2:converter-moshi:2.9.0")
        add("implementation", "com.squareup.okhttp3:okhttp-bom:4.10.0")
        add("implementation", "com.squareup.okhttp3:logging-interceptor:4.10.0")
        add("implementation", "com.squareup.okhttp3:okhttp")
    }
}

fun Project.addMoshi() {
    dependencies {
        add("kapt", "com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
        add("implementation", "com.squareup.moshi:moshi:1.14.0")
        add("implementation", "com.squareup.moshi:moshi-adapters:1.14.0")
        add("implementation", "com.squareup.moshi:moshi-kotlin:1.14.0")

    }
}


fun Project.addCoroutine() {
    dependencies {
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1")
    }
}

fun Project.addCoil() {
    dependencies {
        add("implementation", "io.coil-kt:coil-compose:2.1.0")
        add("implementation", "androidx.appcompat:appcompat:1.4.2")
    }
}

fun Project.addRoom() {
    dependencies {
        val roomVersion = "2.4.3"
        add("implementation", "androidx.room:room-runtime:$roomVersion")
        add("kapt", "androidx.room:room-compiler:$roomVersion")
        add("implementation", "androidx.room:room-ktx:$roomVersion")
    }
}
