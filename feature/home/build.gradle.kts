plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.home"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(libs.coil)
    implementation(libs.google.material)
    implementation(libs.androidx.recyclerview)
}
