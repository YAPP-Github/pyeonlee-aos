plugins {
    id("peonlee.android.library")
    id("peonlee.android.library.compose")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.peonlee.core.ui"

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    implementation(libs.androidx.core.core.ktx)
    implementation(libs.google.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.paging3)
    implementation(libs.coil)
}
