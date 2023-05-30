package com.redhaputra.pokemonlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redhaputra.commons.ui.base.BaseFragment
import com.redhaputra.pokemonlist.adapter.PokemonListLoadStateAdapter
import com.redhaputra.pokemonlist.adapter.PokemonListPagingAdapter
import com.redhaputra.pokemonlist.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

/**
 * Pokemon List Fragment to show pokemon list.
 */
@AndroidEntryPoint
class PokemonListFragment :
    BaseFragment<FragmentPokemonListBinding>(R.layout.fragment_pokemon_list),
    PokemonListListener {

    private val viewModel: PokemonListViewModel by viewModels()
    private val adapter by lazy {
        PokemonListPagingAdapter(this, Glide.with(this)).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupPagingAdapter()
        setupPagingListener()
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    override fun onClick(pokemonName: String?) {
        // go to detail pokemon
    }

    private fun setupPagingAdapter() {
        val layoutManager = LinearLayoutManager(requireContext())
        viewBinding.recyclerView.layoutManager = layoutManager
        viewBinding.recyclerView.adapter = adapter.withLoadStateFooter(
            PokemonListLoadStateAdapter {
                adapter.retry()
            }
        )
    }

    private fun setupPagingListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter {
                    it.refresh is LoadState.Loading && adapter.itemCount == 0
                }
                .collect {
                    showHideShimmer(true)
                }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter {
                    it.refresh is LoadState.NotLoading
                }
                .collect {
                    showHideShimmer(false)
                    showHideLayout()
                }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.Error }
                .collect { loadState ->
                    showHideShimmer(false)
                    showHideLayout()
                    showError(loadState)
                }
        }
    }

    private fun showHideShimmer(visible: Boolean) {
        with(viewBinding.includeShimmer.shimmerListFeedReport) {
            this.isVisible = visible
            viewBinding.swipeLayout.isVisible = false

            if (visible) {
                this.showShimmer(true)
            } else {
                this.hideShimmer()
            }
        }
    }

    private fun showHideLayout() {
        val emptyReport = adapter.snapshot().items.isEmpty()

        viewBinding.swipeLayout.isVisible = true
        adapter.setEmptyState(emptyReport)
        viewModel.setRefresh(false)
    }

    private fun showError(loadState: CombinedLoadStates) {
        val error = when {
            loadState.prepend is LoadState.Error ->
                loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error ->
                loadState.append as LoadState.Error
            loadState.refresh is LoadState.Error ->
                loadState.refresh as LoadState.Error
            else -> null
        }
        error?.let {
            Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.refreshEvent.collectLatest {
                adapter.refresh()
            }
        }

        viewModel.listPager.observe(viewLifecycleOwner) { data ->
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(data)
            }
        }
    }
}