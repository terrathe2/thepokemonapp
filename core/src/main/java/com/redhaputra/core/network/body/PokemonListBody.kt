package com.redhaputra.core.network.body

import androidx.annotation.Keep

/**
 * Pokemon list body data
 */
@Keep
data class PokemonListBody(
    val limit: Int,
    val offset: Int
)