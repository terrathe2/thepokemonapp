package com.redhaputra.detailpokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redhaputra.commons.ui.model.PokemonDetailData
import com.redhaputra.core.network.adapter.NetworkResponse
import com.redhaputra.core.network.body.PokemonDetailBody
import com.redhaputra.core.network.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [DetailPokemonFragment].
 *
 * @see ViewModel
 */
@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private var pokemonName = ""

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _pokemonData = MutableLiveData<PokemonDetailData>()
    val pokemonData: LiveData<PokemonDetailData> = _pokemonData

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> = _errorEvent.asSharedFlow()

    /**
     * Handle set pokemon name for fetch body
     */
    fun setPokemonName(name: String) {
        pokemonName = name
    }

    /**
     * Handle pokemon detail api fetch
     */
    fun getDetailPokemon(name: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val body = PokemonDetailBody(name = name ?: pokemonName)
            val response = pokemonRepository.getPokemonDetail(body)
            withContext(Dispatchers.Main) {
                when (response) {
                    is NetworkResponse.Success -> {
                        response.data?.let { poke ->
                            val pokemonDisplayName = poke.name.replaceFirstChar { c ->
                                if (c.isLowerCase()) {
                                    c.titlecase(Locale.getDefault())
                                } else {
                                    c.toString()
                                }
                            }
                            _pokemonData.value = PokemonDetailData(
                                name = pokemonDisplayName,
                                imgUrl = poke.sprites.image,
                                weight = "${(poke.weight/10)} kg",
                                moves = poke.moves.map { it.move.name },
                                types = poke.types.map { it.type.name }
                            )
                        }
                    }
                    is NetworkResponse.Error -> _errorEvent.emit(response.message)
                }
                _isLoading.value = false
                _isRefreshing.value = false
            }
        }
    }

    /**
     * Refresh list report data
     */
    fun onRefresh() {
        _isRefreshing.value = true
        viewModelScope.launch {
            getDetailPokemon()
        }
    }
}