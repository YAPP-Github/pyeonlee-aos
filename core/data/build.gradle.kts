plugins {
    id("peonlee.android.library")
    id("peonlee.android.hilt")
    id("peonlee.android.repository")
    kotlin("plugin.serialization") version "1.4.21"
}

android {
    namespace = "com.peonlee.core.data"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:common"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging3)
    implementation(libs.androidx.test.runner)
}
