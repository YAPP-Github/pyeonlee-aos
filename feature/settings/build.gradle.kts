plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.settings"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":feature:termsdetail"))
}
