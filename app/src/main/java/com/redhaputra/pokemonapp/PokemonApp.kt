package com.redhaputra.pokemonapp

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Application class for maintaining global application state.
 *
 * @see Application
 */
@HiltAndroidApp
class PokemonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initStetho()
        initTimber()
    }

    /**
     *  Network logger initialization
     */
    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    /**
     * Simple console logger initialization
     */
    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}