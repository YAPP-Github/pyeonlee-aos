// Root build.gradle.kts
buildscript {
    dependencies {
        classpath(libs.google.service)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt.gradle) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.ktlint.gradle) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.google.service) apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
