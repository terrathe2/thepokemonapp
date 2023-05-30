package com.redhaputra.pokemonlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.redhaputra.commons.ui.base.BaseViewHolder
import com.redhaputra.commons.ui.model.PokemonData
import com.redhaputra.pokemonlist.PokemonListListener
import com.redhaputra.pokemonlist.R as RC
import com.redhaputra.pokemonlist.databinding.ItemListPokemonBinding

/**
 * Class describes Pokemon list item view and metadata about its place within RecyclerView.
 *
 * @see BaseViewHolder
 */
class PokemonListViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup,
    private val listener: PokemonListListener
) : BaseViewHolder<ItemListPokemonBinding>(
    ItemListPokemonBinding.inflate(inflater, parent, false)
) {
    /**
     * Bind view data binding variables.
     *
     * @param lap Laporan list item.
     */
    fun bind(data: PokemonData, glide: RequestManager) {
        binding.item = data
        binding.root.setOnClickListener {
            listener.onClick(data.name)
        }
        binding.ivPokemon.setPadding(0, 0, 0, 0)
        glide.load(data.imgUrl)
            .centerCrop()
            .placeholder(RC.drawable.empty_pokemon_img_placeholder)
            .into(binding.ivPokemon)
        binding.executePendingBindings()
    }
}