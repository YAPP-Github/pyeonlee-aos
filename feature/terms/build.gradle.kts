plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.terms"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":feature:termsdetail"))
}
