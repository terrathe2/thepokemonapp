package com.redhaputra.core.network.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response for Pokemon sprites
 */
@Keep
@JsonClass(generateAdapter = true)
data class PokemonSprites(
    @Json(name = "front_default")
    val image: String
)