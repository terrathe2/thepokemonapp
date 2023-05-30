import dependencies.Dependencies

plugins {
    id("commons.my-android-lib")
}

android {
    namespace = "com.redhaputra.commons.ui"
}

dependencies {
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.RECYCLE_VIEW)
}