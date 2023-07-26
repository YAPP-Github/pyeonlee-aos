plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.withdrawal"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
}
