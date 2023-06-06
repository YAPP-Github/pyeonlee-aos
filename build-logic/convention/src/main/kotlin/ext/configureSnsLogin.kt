package ext

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import const.Keys
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.io.File

internal fun Project.configureSnsLogin(
    commonExtension: CommonExtension<*, *, *, *>
) {

    commonExtension.apply {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        defaultConfig {
            buildConfigField(
                type = "String",
                name = "KAKAO_NATIVE_KEY",
                value = getApiKey(
                    propertyKey = Keys.KAKAO_NATIVE_APP_KEY,
                    rootDirectory = rootDir
                )
            )

            buildConfigField(
                type = "String",
                name = "GOOGLE_CLIENT_ID",
                value = getApiKey(
                    propertyKey = Keys.GOOGLE_CLIENT_ID,
                    rootDirectory = rootDir
                )
            )

            manifestPlaceholders[Keys.KAKAO_KEY] = getApiKey(
                propertyKey = Keys.KAKAO_NATIVE_APP_KEY,
                rootDirectory = rootDir
            ).replace("\"", "")
        }

        buildFeatures {
            buildConfig = true
        }

        dependencies {
            "implementation"(libs.findLibrary("android.kakao.login").get())
        }
    }
}

private fun getApiKey(propertyKey: String, rootDirectory: File): String {
    return gradleLocalProperties(rootDirectory).getProperty(propertyKey)
}
