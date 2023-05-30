import dependencies.Dependencies

plugins {
    id("commons.my-android-feature")
}

android {
    namespace = "com.redhaputra.detailpokemon"
}

dependencies {
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)
}