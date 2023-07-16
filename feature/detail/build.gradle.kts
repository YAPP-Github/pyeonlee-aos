plugins {
    id("peonlee.android.feature")
    id("org.jetbrains.kotlin.android")
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
    implementation("androidx.paging:paging-common-ktx:3.1.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
}
