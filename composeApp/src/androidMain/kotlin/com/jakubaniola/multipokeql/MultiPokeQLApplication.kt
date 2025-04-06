package com.jakubaniola.multipokeql

import android.app.Application
import com.jakubaniola.multipokeql.init.initKoin

class MultiPokeQLApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}