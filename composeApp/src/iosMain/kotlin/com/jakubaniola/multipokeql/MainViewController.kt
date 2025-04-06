package com.jakubaniola.multipokeql

import androidx.compose.ui.window.ComposeUIViewController
import com.jakubaniola.multipokeql.home.compose.HomeScreen
import com.jakubaniola.multipokeql.init.initKoin

@Suppress("unused") // Called from Swift
fun MainViewController() = ComposeUIViewController { HomeScreen() }

@Suppress("unused") // Called from Swift
fun initApp() = initKoin()