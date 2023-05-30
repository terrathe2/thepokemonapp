plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    implementation("com.android.tools.build:gradle:8.0.2")
    implementation(kotlin("gradle-plugin", "1.8.10"))
    implementation("com.github.ben-manes:gradle-versions-plugin:0.46.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.44")
}