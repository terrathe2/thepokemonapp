package com.redhaputra.pokemonlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.redhaputra.commons.ui.base.BaseViewHolder
import com.redhaputra.pokemonlist.databinding.EmptyListPokemonBinding

/**
 * Class describes pokemon list empty state view within the RecyclerView.
 *
 * @see BaseViewHolder
 */
class PokemonListEmptyStateViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
) : BaseViewHolder<EmptyListPokemonBinding>(
    EmptyListPokemonBinding.inflate(inflater, parent, false)
)