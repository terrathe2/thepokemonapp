package com.redhaputra.pokemonlist

/**
 * Listener for pokemon list
 */
interface PokemonListListener {
    /**
     * Action on clicked pokemon
     */
    fun onClick(pokemonName: String?)
}