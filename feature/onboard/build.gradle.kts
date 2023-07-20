plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonleeonboard"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":feature:evaluate"))
}
