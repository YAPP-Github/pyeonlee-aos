plugins {
    id("peonlee.android.application")
    id("peonlee.android.application.compose")
    id("peonlee.android.hilt")
}

android {
    namespace = "com.peonlee"
    defaultConfig {
        applicationId = "com.peonlee"
        versionCode = 1
        versionName = "1.0"
    }
//    packagingOptions {
//        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
//    }
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":feature-mymodel"))
}
