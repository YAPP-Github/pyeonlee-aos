plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.onboading"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":feature:evaluate"))
    implementation(project(":feature:main"))
}
