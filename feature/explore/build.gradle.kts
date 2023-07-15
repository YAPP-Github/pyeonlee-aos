plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.explore"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":feature:product"))
    implementation(libs.google.material)
}
