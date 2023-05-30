package com.redhaputra.core.network.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response for Pokemon detail move
 */
@Keep
@JsonClass(generateAdapter = true)
data class PokemonDetailMove(
    @Json(name = "move")
    val move: Move
)

/**
 * Response for Pokemon move
 */
@Keep
@JsonClass(generateAdapter = true)
data class Move(
    @Json(name = "name")
    val name: String
)