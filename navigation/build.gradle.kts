import dependencies.Dependencies

plugins {
    id("commons.my-android-lib")
}

android {
    namespace = "com.redhaputra.navigation"
}

dependencies {
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
}