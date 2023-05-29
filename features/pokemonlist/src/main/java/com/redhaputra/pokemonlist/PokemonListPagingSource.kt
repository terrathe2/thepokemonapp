package com.redhaputra.pokemonlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.redhaputra.core.network.adapter.NetworkResponse
import com.redhaputra.core.network.body.PokemonListBody
import com.redhaputra.core.network.repositories.PokemonRepository
import com.redhaputra.core.network.responses.PokemonListItemResponse
import timber.log.Timber

/**
 * It is used to load pages of Pokemon List data
 */
class PokemonListPagingSource(
    private val pokemonRepository: PokemonRepository
) : PagingSource<Int, PokemonListItemResponse>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonListItemResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListItemResponse> {
        val pageNumber = params.key ?: 1
        // Since 1 is the lowest page number, return null to signify no more pages should
        // be loaded before it.
        val prevKey = if (pageNumber <= 1) null else pageNumber - 1
        val loadSize = params.loadSize
        val param = PokemonListBody(limit = loadSize, offset = pageNumber * loadSize)
        val response = pokemonRepository.getPokemonList(param)
        return when (response) {
            is NetworkResponse.Success -> {
                val data = response.data?.pokemonList ?: listOf()
                val nextKey =
                    if (data.isNotEmpty() && data.size >= loadSize) pageNumber + 1 else null
                LoadResult.Page(data, prevKey, nextKey)
            }
            is NetworkResponse.Error -> LoadResult.Error(Throwable(response.message))
        }
    }
}