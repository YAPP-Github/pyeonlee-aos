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
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}
