plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.review"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.coil)
    implementation("com.google.android.material:material:1.9.0")
}
