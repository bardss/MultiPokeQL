package com.jakubaniola.multipokeql.ui.appnavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jakubaniola.multipokeql.ui.home.compose.HomeScreen
import com.jakubaniola.multipokeql.ui.pokemon.compose.PokemonDetailsScreen

sealed interface Screen {
    data object Home : Screen
    data class PokemonDetails(val pokemonKey: String) : Screen
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (val screen = currentScreen) {
        is Screen.Home -> HomeScreen(
            navigateToDetails = { currentScreen = Screen.PokemonDetails(it) }
        )

        is Screen.PokemonDetails -> PokemonDetailsScreen(
            pokemonKey = screen.pokemonKey,
            navigateBack = { currentScreen = Screen.Home },
        )
    }
}