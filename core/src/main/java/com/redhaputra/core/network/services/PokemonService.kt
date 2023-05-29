package com.redhaputra.core.network.services

import com.redhaputra.core.network.responses.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Representation interface of the Pokemon API endpoints.
 */
interface PokemonService {
    /**
     * Get Pokemon list
     */
    @GET("pokemon")
    suspend fun getPokemonList(
        @QueryMap query: Map<String, Any>
    ): Response<PokemonListResponse>
}