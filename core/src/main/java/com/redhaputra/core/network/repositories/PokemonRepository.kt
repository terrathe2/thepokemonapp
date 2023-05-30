package com.redhaputra.core.network.repositories

import com.redhaputra.core.network.adapter.NetworkResponse
import com.redhaputra.core.network.body.PokemonDetailBody
import com.redhaputra.core.network.body.PokemonListBody
import com.redhaputra.core.network.responses.PokemonDetailResponse
import com.redhaputra.core.network.responses.PokemonListItemResponse
import com.redhaputra.core.network.responses.PokemonListResponse
import com.redhaputra.core.network.services.PokemonService
import timber.log.Timber
import java.io.IOException

/**
 * Repository module for handling pokemon api response operations.
 */
class PokemonRepository(
    private val service: PokemonService,
) {
    /**
     * Get pokemon list
     *
     * @param params Parameter that needed for fetch Pokemon List
     */
    suspend fun getPokemonList(params: PokemonListBody): NetworkResponse<PokemonListResponse> {
        val queryMap = mapOf(
            "limit" to params.limit,
            "offset" to params.offset
        )

        try {
            val request = service.getPokemonList(queryMap)
            if (request.isSuccessful) {
                val body = request.body()
                return NetworkResponse.Success(body)
            }

            throw Exception("Unknown Error")
        } catch (e: Exception) {
            if (e is IOException) {
                return NetworkResponse.Error(e.message ?: "connection error", 0)
            }

            return NetworkResponse.Error(e.message ?: "server error", 99)
        }
    }

    /**
     * Get pokemon detail
     *
     * @param params Parameter that needed for fetch Pokemon detail
     */
    suspend fun getPokemonDetail(params: PokemonDetailBody): NetworkResponse<PokemonDetailResponse> {
        try {
            val request = service.getPokemonDetail(params.name)
            if (request.isSuccessful) {
                val body = request.body()
                return NetworkResponse.Success(body)
            }

            throw Exception("Unknown Error")
        } catch (e: Exception) {
            if (e is IOException) {
                return NetworkResponse.Error(e.message ?: "connection error", 0)
            }

            return NetworkResponse.Error(e.message ?: "server error", 99)
        }
    }

}