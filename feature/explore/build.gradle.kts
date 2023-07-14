plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.explore"
    viewBinding { enable = true }
}

dependencies {
    implementation(libs.google.material)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
}
