import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.addCompose() {
    dependencies {
        val composeVersion = "1.2.1"
        add("implementation", "androidx.compose.ui:ui:$composeVersion")
        add("implementation", "androidx.compose.material:material:$composeVersion")
        add("implementation", "androidx.compose.material:material-icons-extended:$composeVersion")
        add("implementation", "androidx.compose.ui:ui-tooling-preview:$composeVersion")
        add("debugImplementation", "androidx.compose.ui:ui-tooling:$composeVersion")
        add("implementation", "androidx.activity:activity-compose:1.3.1")
        add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
        add("implementation",  "com.google.android.material:material:1.2.1")
        add("implementation", "androidx.navigation:navigation-compose:2.5.0-beta01")
        add("implementation" ,"com.google.accompanist:accompanist-navigation-animation:0.24.0-alpha")

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
