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
    implementation("com.android.tools.build:gradle:7.4.0")
    implementation(kotlin("gradle-plugin", "1.7.21"))
    implementation("com.github.ben-manes:gradle-versions-plugin:0.46.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.44")
}