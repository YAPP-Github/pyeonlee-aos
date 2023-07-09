plugins {
    id("peonlee.android.library")
}

android {
    namespace = "com.peonlee.model"
}

dependencies {
    implementation(project(":core:data"))
}
