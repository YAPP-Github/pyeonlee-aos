plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.explore"
    viewBinding { enable = true }
}

dependencies {
    implementation(libs.androidx.recyclerview)
}
