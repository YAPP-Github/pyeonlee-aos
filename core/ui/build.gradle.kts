plugins {
    id("peonlee.android.library")
    id("peonlee.android.library.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.peonlee.core.ui"
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}
