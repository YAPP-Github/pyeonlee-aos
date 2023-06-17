plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.home"
    viewBinding { enable = true }
}

dependencies {
    implementation(libs.androidx.recyclerview)
}
