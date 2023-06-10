import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("peonlee.android.library")
                apply("peonlee.android.library.compose")
                apply("peonlee.android.hilt")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                // Instrumented tests
                "androidTestImplementation"(
                    libs.findLibrary("androidx.compose.ui.test.junit4").get()
                )
                // Hilt and instrumented tests.
                "androidTestImplementation"(libs.findLibrary("hilt.android.testing").get())
                "kaptAndroidTest"(libs.findLibrary("hilt.android.compiler").get())
                // Hilt and Robolectric tests.
                "testImplementation"(libs.findLibrary("hilt.android.testing").get())
                "kaptTest"(libs.findLibrary("hilt.android.compiler").get())
                // Instrumented tests: jUnit rules and runners
                "androidTestImplementation"(libs.findLibrary("androidx.test.ext.junit").get())
                "androidTestImplementation"(libs.findLibrary("androidx.test.runner").get())
                "androidTestImplementation"(libs.findLibrary("androidx.recyclerview").get())
                "implementation"(libs.findLibrary("androidx.appcompat").get())
            }
        }
    }
}
