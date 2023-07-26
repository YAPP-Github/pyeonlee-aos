plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.user"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":feature:settings"))
}
