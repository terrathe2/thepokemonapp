package com.redhaputra.core.di.module

import com.redhaputra.core.network.services.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Object module that provide all the service needed to handle API Fetch.
 *
 * @see Module
 */
@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object ServiceModule {
    /**
     * Create a provider method binding for [PokemonService].
     *
     * @return Instance of Pokemon service.
     * @see Provides
     */
    @Singleton
    @Provides
    fun providePokemonService(
        @Named("unAuthClient") client: OkHttpClient,
        retrofitBuilder: Retrofit.Builder
    ): PokemonService = retrofitBuilder
        .client(client)
        .build()
        .create(PokemonService::class.java)
}