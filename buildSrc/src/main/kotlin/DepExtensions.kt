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
        add("implementation", "androidx.navigation:navigation-compose:2.5.0-beta01")
    }
}
