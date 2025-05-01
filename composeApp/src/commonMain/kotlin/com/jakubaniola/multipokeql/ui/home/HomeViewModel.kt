package com.jakubaniola.multipokeql.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.multipokeql.domain.GetPokemonsUseCase
import com.jakubaniola.multipokeql.domain.PokemonListItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val getPokemons: GetPokemonsUseCase,
) : ViewModel() {
    val uiState: StateFlow<UiState> = flow {
        emit(UiState.Loaded(getPokemons().toUiModel()))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    private fun List<PokemonListItem>.toUiModel() = map { it.toUiModel() }

    private fun PokemonListItem.toUiModel() = PokemonListUiModel(
        pokedexId = pokedexNum,
        name = name.capitalize(),
        imageUrl = imageUrl,
    )

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