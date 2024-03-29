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
    implementation(project(":feature:user"))
    implementation(project(":feature:evaluate"))
    implementation(project(":core:model"))

    implementation(libs.google.material)
    implementation(libs.bundles.fragment)
}
