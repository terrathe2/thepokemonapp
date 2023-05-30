package com.redhaputra.core.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.redhaputra.core.network.UnauthorizedInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Object module that provide all the network config needed.
 *
 * @see Module
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private const val TIMEOUT: Long = 30

    /**
     * Create a provider method binding for [Moshi].
     *
     * @return Instance of moshi.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Create a provider method binding for [OkHttpClient]
     * without any authenticator
     *
     * @return Instance of http client.
     * @see Provides
     */
    @Provides
    @Named("unAuthClient")
    fun provideUnauthHttpClient(
        clientBuilder: OkHttpClient.Builder,
        unauthorizedInterceptor: UnauthorizedInterceptor
    ): OkHttpClient = clientBuilder
        .addInterceptor(unauthorizedInterceptor)
        .build()

    /**
     * Create a base provider method binding for [OkHttpClient.Builder].
     *
     * @return Instance of OkHttpClient Builder.
     * @see Provides
     */
    @Provides
    fun provideBasicOkhttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .callTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
    }

    /**
     * Create a provider method binding for [Retrofit.Builder].
     *
     * @return Instance of retrofit builder.
     * @see Provides
     */
    @Provides
    fun provideRetrofitBuilder(moshi: Moshi): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))

    @Provides
    fun provideUnauthorizedInterceptor(
        moshi: Moshi
    ) = UnauthorizedInterceptor(moshi)
}