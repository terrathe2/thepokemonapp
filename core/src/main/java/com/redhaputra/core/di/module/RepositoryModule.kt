package com.redhaputra.core.di.module

import com.redhaputra.core.network.repositories.PokemonRepository
import com.redhaputra.core.network.services.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Object module that provide all the repository needed to handle API implementation.
 *
 * @see Module
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Create a provider method binding for [PokemonRepository].
     *
     * @return Instance of Pokemon Repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun providePokemonRepository(service: PokemonService) = PokemonRepository(service)
}