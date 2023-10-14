plugins {
    id("peonlee.android.application")
    id("peonlee.android.application.compose")
    id("peonlee.android.hilt")
    id("peonlee.android.sns.login.application")
    id("com.google.firebase.crashlytics")
    alias(libs.plugins.google.service)
}

android {
    namespace = "com.peonlee"
    defaultConfig {
        applicationId = "com.peonlee"
        versionCode = 6
        versionName = "1.0.0"
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:login"))
    implementation(project(":feature:onboard"))
    implementation(project(":feature:main"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:review"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:evaluate"))
    implementation(project(":feature:user"))
    implementation(project(":core:model"))
    implementation(project((":feature:event")))

    implementation(project(":core:data"))

    implementation(libs.androidx.core.splashscreen)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}
