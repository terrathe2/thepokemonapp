package com.redhaputra.core.network.repositories

import com.redhaputra.core.network.adapter.NetworkResponse
import com.redhaputra.core.network.body.PokemonListBody
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
     * @param params Parameter that needed for fetch My Task List
     */
    suspend fun getPokemonList(params: PokemonListBody): NetworkResponse<PokemonListResponse> {
        val queryMap = mapOf(
            "limit" to params.limit,
            "offset" to params.offset
        )

        try {
            val request = service.getPokemonList(queryMap)
            Timber.d("Masuk sini ${request.isSuccessful}")
            if (request.isSuccessful) {
                val body = request.body()
                Timber.d("Masuk sini ${body?.pokemonList}")
                return NetworkResponse.Success(body)
            }

            throw Exception("Unknown Error")
        } catch (e: Exception) {
            Timber.d("Masuk sini error $e")
            if (e is IOException) {
                return NetworkResponse.Error(e.message ?: "connection error", 0)
            }

            return NetworkResponse.Error(e.message ?: "server error", 99)
        }
    }

}