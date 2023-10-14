plugins {
    id("peonlee.android.feature")
}

android {
    namespace = "com.peonlee.event"
    viewBinding { enable = true }
}

dependencies {
    implementation(project(":core:model"))
}
