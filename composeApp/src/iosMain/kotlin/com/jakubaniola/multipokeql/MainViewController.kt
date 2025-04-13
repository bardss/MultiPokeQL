package com.jakubaniola.multipokeql

import androidx.compose.ui.window.ComposeUIViewController
import com.jakubaniola.multipokeql.ui.home.compose.HomeScreen

@Suppress("unused") // Called from Swift
fun MainViewController() = ComposeUIViewController { HomeScreen() }