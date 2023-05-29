import dependencies.Dependencies

plugins {
    id("commons.my-android-feature")
}

android {
    namespace = "com.redhaputra.pokemonlist"
}

dependencies {
    implementation(Dependencies.PAGING3)
}