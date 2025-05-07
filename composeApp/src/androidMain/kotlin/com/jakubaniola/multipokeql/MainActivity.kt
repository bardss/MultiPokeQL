package com.jakubaniola.multipokeql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jakubaniola.multipokeql.ui.appnavigation.AppNavigation
import com.jakubaniola.multipokeql.navigation.AndroidBrowserNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val browserNavigator = AndroidBrowserNavigator(this)

        setContent {
            AppNavigation(browserNavigator)
        }
    }
}
