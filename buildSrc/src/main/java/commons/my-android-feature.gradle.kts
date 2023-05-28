package commons

import dependencies.Dependencies
import gradle.kotlin.dsl.accessors._82d2c2d6084bd92195563500a586d689.android
import gradle.kotlin.dsl.accessors._82d2c2d6084bd92195563500a586d689.implementation
import gradle.kotlin.dsl.accessors._82d2c2d6084bd92195563500a586d689.kapt
import gradle.kotlin.dsl.accessors._82d2c2d6084bd92195563500a586d689.kotlinOptions
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    kapt {
        useBuildCache = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.TIMBER)

    // HILT
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_COMPILER)
}