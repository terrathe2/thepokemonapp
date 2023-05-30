package com.redhaputra.core.network.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Response for Pokemon List
 */
@Keep
@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    @Json(name = "results")
    val pokemonList: List<PokemonListItemResponse> = listOf()

)