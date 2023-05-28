package dependencies

import DependenciesVersions

object Dependencies {
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${DependenciesVersions.CONSTRAINT_LAYOUT}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${DependenciesVersions.FRAGMENT_KTX}"

    const val HILT = "com.google.dagger:hilt-android:${DependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${DependenciesVersions.HILT}"

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${DependenciesVersions.KOTLIN}"

    const val MATERIAL = "com.google.android.material:material:${DependenciesVersions.MATERIAL}"

    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${DependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${DependenciesVersions.NAVIGATION}"
    const val TIMBER = "com.jakewharton.timber:timber:${DependenciesVersions.TIMBER}"
}