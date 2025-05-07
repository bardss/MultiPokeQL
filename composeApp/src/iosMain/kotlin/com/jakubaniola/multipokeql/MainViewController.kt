package com.jakubaniola.multipokeql

import androidx.compose.ui.window.ComposeUIViewController
import com.jakubaniola.multipokeql.navigation.iOSBrowserNavigator
import com.jakubaniola.multipokeql.ui.appnavigation.AppNavigation

@Suppress("unused") // Called from Swift
fun MainViewController() = ComposeUIViewController {
    val browserNavigator = iOSBrowserNavigator()
    AppNavigation(browserNavigator)
}
