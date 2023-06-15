plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.review"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:ui"))
}
