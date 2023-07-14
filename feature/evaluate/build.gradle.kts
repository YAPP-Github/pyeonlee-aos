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
    implementation(project(":feature:main"))

    implementation(libs.google.material)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.paging3)
    implementation(libs.coil)
}
