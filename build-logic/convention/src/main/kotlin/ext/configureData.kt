package ext

import com.android.build.api.dsl.CommonExtension
import const.Keys
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureData(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        defaultConfig {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = getApiKey(
                    propertyKey = Keys.BASE_URL,
                    rootDirectory = rootDir
                )
            )
        }

        buildFeatures {
            buildConfig = true
        }

        dependencies {
            "implementation"(libs.findLibrary("squareup.okhttp3").get())
            "implementation"(libs.findLibrary("squareup.okhttp3.logging.interceptor").get())
            "implementation"(libs.findLibrary("squareup.retrofit2").get())
            "implementation"(libs.findLibrary("serialization.converter").get())
            "implementation"(libs.findLibrary("serialization.json").get())
        }
    }
}
