plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.main"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":feature:home"))
    implementation(project(":feature:explore"))
    implementation(libs.bundles.fragment)
}
