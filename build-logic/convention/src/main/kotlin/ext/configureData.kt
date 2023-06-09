package ext

import com.android.build.api.dsl.CommonExtension
import const.Keys
import org.gradle.api.Project

internal fun Project.configureData(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
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
    }
}
