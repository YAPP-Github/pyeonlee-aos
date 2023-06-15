plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.main"
    viewBinding { enable = true }
}

dependencies {
    implementation(libs.bundles.fragment)
}
