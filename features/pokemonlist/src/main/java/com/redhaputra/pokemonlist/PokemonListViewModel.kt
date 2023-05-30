package com.redhaputra.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.redhaputra.commons.ui.model.PokemonData
import com.redhaputra.core.network.repositories.PokemonRepository
import com.redhaputra.core.network.responses.PokemonListItemResponse
import com.redhaputra.core.network.responses.asExternalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [PokemonListFragment].
 *
 * @see ViewModel
 */
@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 10
    }

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _refreshEvent = MutableSharedFlow<String>()
    val refreshEvent: SharedFlow<String> = _refreshEvent.asSharedFlow()

    private val pagingConfig = PagingConfig(PAGE_SIZE, initialLoadSize = PAGE_SIZE)
    val listPager: LiveData<PagingData<PokemonData>> =
        Pager(config = pagingConfig) {
            PokemonListPagingSource(pokemonRepository = pokemonRepository)
        }.flow
            .map { it.map(PokemonListItemResponse::asExternalModel) }
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .asLiveData()

    /**
     * Refresh list report data
     */
    fun onRefresh() {
        viewModelScope.launch {
            Timber.d("masuk sini refresh")
            setRefresh(true)
            _refreshEvent.emit("refresh")
        }
    }

    /**
     * Handle refresh flag
     */
    fun setRefresh(value: Boolean) {
        _isRefreshing.value = value
    }
}