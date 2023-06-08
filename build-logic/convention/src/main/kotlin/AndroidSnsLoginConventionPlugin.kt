import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import ext.configureSnsLogin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

// App 모듈 전용 플러그인
class AndroidSnsLoginApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureSnsLogin(this)
            }
        }
    }
}

// Feature 모듈 전용 플러그인
class AndroidSnsLoginFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureSnsLogin(this)
            }
        }
    }
}