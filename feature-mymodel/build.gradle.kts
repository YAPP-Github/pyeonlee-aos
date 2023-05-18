plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.feature.mymodel"
}

dependencies {
    implementation(project(":core-data"))
    implementation(project(":core-ui"))
    androidTestImplementation(project(":core-testing"))
}
