package com.jakubaniola.multipokeql.ui.home.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.multipokeql.designsystem.AppTheme
import com.jakubaniola.multipokeql.ui.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    AppTheme {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        HomeContent(uiState)
    }
}

@Composable
fun HomeContent(uiState: HomeViewModel.UiState) {
    Scaffold {
        when (uiState) {
            is HomeViewModel.UiState.Loaded -> HomeList(uiState)
            is HomeViewModel.UiState.Loading -> LoadingScreen()
        }
    }
}

@Composable
fun HomeList(uiState: HomeViewModel.UiState.Loaded) {
    LazyColumn {
        items(uiState.items.size) { index ->
            val item = uiState.items[index]
            PokemonListEntry(
                pokemonName = item.name,
                pokedexNumber = item.pokedexId.toInt(),
                imageUrl = item.imageUrl
            )
        }
    }
}