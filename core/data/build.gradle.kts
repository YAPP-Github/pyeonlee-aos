plugins {
    id("peonlee.android.library")
    id("peonlee.android.hilt")
    id("peonlee.android.data")
}

android {
    namespace = "com.peonlee.core.data"
}

dependencies {
    implementation(project(":core:database"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.test.runner)
}
