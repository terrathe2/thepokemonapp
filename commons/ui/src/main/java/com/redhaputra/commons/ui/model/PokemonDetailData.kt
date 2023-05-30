package com.redhaputra.commons.ui.model

import androidx.annotation.Keep

/**
 * Detailed pokemon data
 */
@Keep
data class PokemonDetailData(
    val moves: List<String> = listOf(),
    val name: String,
    val imgUrl: String,
    val types: List<String> = listOf(),
    val weight: String // in Kg
)
