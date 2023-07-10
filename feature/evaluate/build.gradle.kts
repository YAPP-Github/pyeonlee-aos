plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.evaluate"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    implementation(libs.androidx.recyclerview)
}
