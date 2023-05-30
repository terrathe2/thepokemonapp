package com.redhaputra.pokemonlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.redhaputra.commons.ui.base.BaseViewHolder
import com.redhaputra.pokemonlist.databinding.ItemNetworkStateBinding
import timber.log.Timber

/**
 * Class describes list network state view within the recyclerview.
 *
 * @see BaseViewHolder
 */
class NetworkStateItemViewHolder(
    private val retry: () -> Unit,
    parent: ViewGroup,
    inflater: LayoutInflater,
) : BaseViewHolder<ItemNetworkStateBinding>(
    ItemNetworkStateBinding.inflate(inflater, parent, false)
) {
    /**
     * func to Bind view data binding variables.
     *
     * @param loadState Load state.
     */
    fun bind(loadState: LoadState) {
        binding.progressErrorLoadmore.isVisible = loadState is LoadState.Loading
        binding.layoutErrorLoadmore.isVisible = loadState is LoadState.Error
        binding.layoutErrorLoadmore.setOnClickListener {
            retry()
        }
    }
}