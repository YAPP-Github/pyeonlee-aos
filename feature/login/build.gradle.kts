
plugins {
    id("peonlee.android.feature")
    id("peonlee.android.sns.login.feature")
}

android {
    namespace = "com.peonlee.login"

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}
