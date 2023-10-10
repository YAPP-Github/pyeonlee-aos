plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.storage"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
}
