package com.redhaputra.core.network.responses

import androidx.annotation.Keep
import com.redhaputra.commons.ui.model.PokemonData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import timber.log.Timber
import java.util.Locale

/**
 * Response Item for Pokemon List
 */
@Keep
@JsonClass(generateAdapter = true)
data class PokemonListItemResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val pokemonUrl: String
)

fun PokemonListItemResponse.asExternalModel(): PokemonData {
    val pokedexEntry = if (pokemonUrl.endsWith("/")) {
        pokemonUrl.dropLast(1).takeLastWhile { it.isDigit() }
    } else {
        pokemonUrl.takeLastWhile { it.isDigit() }
    }
    val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokedexEntry.png"
    return PokemonData(
        name = name,
        displayName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
        imgUrl = imgUrl,
    )
}



