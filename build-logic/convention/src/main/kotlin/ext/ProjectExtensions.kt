package ext

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import java.io.File

fun Project.getApiKey(propertyKey: String, rootDirectory: File): String {
    return gradleLocalProperties(rootDirectory).getProperty(propertyKey)
}
