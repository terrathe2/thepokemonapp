package com.redhaputra.core.network.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response for Pokemon detail
 */
@Keep
@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @Json(name = "moves")
    val moves: List<PokemonDetailMove> = listOf(),
    @Json(name = "name")
    val name: String,
    @Json(name = "sprites")
    val sprites: PokemonSprites,
    @Json(name = "types")
    val types: List<PokemonDetailTypes> = listOf(),
    @Json(name = "weight")
    val weight: Int
)