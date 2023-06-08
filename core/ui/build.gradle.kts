plugins {
    id("peonlee.android.library")
    id("peonlee.android.library.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.peonlee.core.ui"

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(libs.androidx.core.core.ktx)
    implementation(libs.google.material)
    implementation(libs.androidx.constraintlayout)
}
