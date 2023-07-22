plugins {
    id("peonlee.android.feature")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
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
    implementation(libs.androidx.paging3)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
}
