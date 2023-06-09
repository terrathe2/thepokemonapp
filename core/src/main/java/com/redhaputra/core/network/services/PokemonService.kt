package com.redhaputra.core.network.services

import com.redhaputra.core.network.responses.PokemonDetailResponse
import com.redhaputra.core.network.responses.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Representation interface of the Pokemon API endpoints.
 */
interface PokemonService {
    /**
     * Get Pokemon list
     */
    @GET("pokemon")
    @JvmSuppressWildcards
    suspend fun getPokemonList(
        @QueryMap query: Map<String, Any>
    ): Response<PokemonListResponse>

    /**
     * Get Pokemon detail
     */
    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): Response<PokemonDetailResponse>
}