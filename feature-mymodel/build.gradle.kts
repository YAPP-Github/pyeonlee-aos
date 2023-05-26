plugins {
    id("peonlee.android.feature")
    id("org.jetbrains.kotlin.android")
    id("peonlee.android.sns.login.feature")
}

android {
    namespace = "com.peonlee.feature.mymodel"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles ("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":core-data"))
    implementation(project(":core-ui"))

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    androidTestImplementation(project(":core-testing"))

    // google
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.play.services.auth)
}
