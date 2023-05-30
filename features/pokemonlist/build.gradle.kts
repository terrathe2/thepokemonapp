import dependencies.Dependencies

plugins {
    id("commons.my-android-feature")
}

android {
    namespace = "com.redhaputra.pokemonlist"
}

dependencies {
    implementation(Dependencies.CARD_VIEW)
    implementation(Dependencies.PAGING3)
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)
    implementation(Dependencies.SHIMMER)
}