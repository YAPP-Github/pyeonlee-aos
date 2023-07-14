plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.feature.detail"

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation("com.google.android.material:material:1.9.0")
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.coil)
    implementation(libs.androidx.cardview)
}
