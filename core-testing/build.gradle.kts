plugins {
    id("peonlee.android.library")
}

android {
    namespace = "com.peonlee.core.testing"
}

dependencies {
    implementation(libs.androidx.test.runner)
    implementation(libs.hilt.android.testing)
}
