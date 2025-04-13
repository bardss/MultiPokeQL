package com.jakubaniola.multipokeql.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {

    private val exampleData = listOf(
        PokemonListUiModel(
            pokedexId = "001",
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        ),
        PokemonListUiModel(
            pokedexId = "002",
            name = "Ivysaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
        ),
        PokemonListUiModel(
            pokedexId = "003",
            name = "Venusaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
        ),
    )

    val uiState: StateFlow<UiState> = flowOf(
        UiState.Loaded(exampleData)
    ).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    sealed interface UiState {
        data object Loading : UiState
        data class Loaded(val items: List<PokemonListUiModel>) : UiState
    }

    data class PokemonListUiModel(
        val pokedexId: String,
        val name: String,
        val imageUrl: String,
    )
}

