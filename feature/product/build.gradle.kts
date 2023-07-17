plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.product"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))

    implementation(libs.google.material)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.paging3)

    implementation(libs.flexbox.layout)
}
