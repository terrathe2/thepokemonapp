package com.redhaputra.pokemonlist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.redhaputra.commons.ui.model.PokemonData
import com.redhaputra.pokemonlist.PokemonListListener
import com.redhaputra.pokemonlist.R

/**
 * Class for presenting Pokemon List data in a [RecyclerView], including computing
 * diffs between Lists on a background thread.
 * @param listener Listener for pokemon item
 *
 * @see PagingDataAdapter
 */
class PokemonListPagingAdapter(
    private val listener: PokemonListListener,
    private val requestManager: RequestManager
) : PagingDataAdapter<PokemonData, RecyclerView.ViewHolder>(POKEMON_COMPARATOR) {

    companion object {
        val POKEMON_COMPARATOR = object : DiffUtil.ItemCallback<PokemonData>() {
            override fun areItemsTheSame(oldItem: PokemonData, newItem: PokemonData) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: PokemonData, newItem: PokemonData) =
                oldItem == newItem
        }
    }

    private var showEmptyStateRow = false

    override fun getItemCount(): Int = super.getItemCount() + if (showEmptyStateRow) 1 else 0

    override fun getItemViewType(position: Int): Int =
        if (showEmptyStateRow) {
            R.layout.empty_list_pokemon
        } else {
            R.layout.item_list_pokemon
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_list_pokemon ->
                if (holder is PokemonListViewHolder) {
                    getItem(position)?.run {
                        holder.bind(this, requestManager)
                    }
                }

            R.layout.empty_list_pokemon -> holder as PokemonListEmptyStateViewHolder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_list_pokemon ->
                PokemonListViewHolder(LayoutInflater.from(parent.context), parent, listener)

            R.layout.empty_list_pokemon ->
                PokemonListEmptyStateViewHolder(LayoutInflater.from(parent.context), parent)

            else -> throw IllegalArgumentException("unknown view type $viewType")
        }

    /**
     * Handle set empty state
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setEmptyState(value: Boolean) {
        if (showEmptyStateRow != value) {
            showEmptyStateRow = value
            notifyDataSetChanged()
        }
    }
}