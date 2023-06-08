plugins {
    id("peonlee.android.library")
    id("peonlee.android.hilt")
}

android {
    namespace = "com.peonlee.core.data"
}

dependencies {
    implementation(project(":core:data"))
}
