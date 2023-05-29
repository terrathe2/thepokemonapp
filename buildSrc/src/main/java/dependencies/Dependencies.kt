package dependencies

import DependenciesVersions

object Dependencies {
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${DependenciesVersions.CONSTRAINT_LAYOUT}"
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependenciesVersions.COROUTINES}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${DependenciesVersions.FRAGMENT_KTX}"
    const val HILT = "com.google.dagger:hilt-android:${DependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${DependenciesVersions.HILT}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${DependenciesVersions.KOTLIN}"
    const val MATERIAL = "com.google.android.material:material:${DependenciesVersions.MATERIAL}"
    const val MOSHI_KTX = "com.squareup.moshi:moshi-kotlin:${DependenciesVersions.MOSHI}"
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${DependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI =
        "androidx.navigation:navigation-ui-ktx:${DependenciesVersions.NAVIGATION}"
    const val PAGING3 =
        "androidx.paging:paging-runtime-ktx:${DependenciesVersions.PAGING3}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER_MOSHI =
        "com.squareup.retrofit2:converter-moshi:${DependenciesVersions.RETROFIT}"
    const val TIMBER = "com.jakewharton.timber:timber:${DependenciesVersions.TIMBER}"
}