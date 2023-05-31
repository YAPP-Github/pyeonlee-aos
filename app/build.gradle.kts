plugins {
    id("peonlee.android.application")
    id("peonlee.android.application.compose")
    id("peonlee.android.hilt")
    id("peonlee.android.sns.login.application")
}

android {
    namespace = "com.peonlee"
    defaultConfig {
        applicationId = "com.peonlee"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:login"))

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation(libs.androidx.core.splashscreen)
}
