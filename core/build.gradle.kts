import BuildModules.Commons
import dependencies.Dependencies
import dependencies.DebugDependencies

plugins {
    id("commons.my-android-lib")
}

android {
    namespace = "com.redhaputra.core"
}

dependencies {
    implementation(project(Commons.UI))

    implementation(Dependencies.MOSHI_KTX)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_MOSHI)

    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)

    debugImplementation(DebugDependencies.STETHO_OKHTTP3)
}