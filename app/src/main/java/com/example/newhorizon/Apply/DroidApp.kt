package com.example.newhorizon.Apply

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class DroidApp : Application() {
    companion object {
        lateinit var instance: DroidApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}