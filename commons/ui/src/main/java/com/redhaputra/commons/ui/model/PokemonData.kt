package com.redhaputra.commons.ui.model

import androidx.annotation.Keep

/**
 * Simple pokemon data
 */
@Keep
data class PokemonData(
    val displayName: String,
    val imgUrl: String,
    val name: String,
)
