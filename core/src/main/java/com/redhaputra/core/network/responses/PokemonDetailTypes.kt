package com.redhaputra.core.network.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response for Pokemon detail types
 */
@Keep
@JsonClass(generateAdapter = true)
data class PokemonDetailTypes(
    @Json(name = "type")
    val type: Type
)

/**
 * Response for Pokemon type
 */
@Keep
@JsonClass(generateAdapter = true)
data class Type(
    @Json(name = "name")
    val name: String
)
