package com.redhaputra.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.redhaputra.commons.ui.BaseFragment
import com.redhaputra.pokemonlist.databinding.FragmentPokemonListBinding
import timber.log.Timber

/**
 * Pokemon List Fragment to show pokemon list.
 */
class PokemonListFragment :
    BaseFragment<FragmentPokemonListBinding>(R.layout.fragment_pokemon_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Masuk sini")
    }
}