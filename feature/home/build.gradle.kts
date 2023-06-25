plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.home"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.google.material)
    implementation(libs.androidx.recyclerview)
}
