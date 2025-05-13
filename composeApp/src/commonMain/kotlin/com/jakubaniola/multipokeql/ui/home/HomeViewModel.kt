package com.jakubaniola.multipokeql.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.multipokeql.domain.GetPokemonsUseCase
import com.jakubaniola.multipokeql.domain.PokemonListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPokemons: GetPokemonsUseCase,
) : ViewModel() {

    private var currentOffset: Int = OFFSET_TO_START_FROM_POKEDEX_1
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadNextPage()
    }

    private fun List<PokemonListItem>.toUiModel() = map { it.toUiModel() }

    private fun PokemonListItem.toUiModel() = PokemonListUiModel(
        pokemonKey = pokemonKey,
        pokedexId = pokedexNum,
        name = name.capitalize(),
        imageUrl = imageUrl,
    )

    fun loadNextPage() = loadPage(currentOffset)

    private fun loadPage(offset: Int) = viewModelScope.launch {
        val pokemonsResult = getPokemons(offset, PAGE_SIZE).toUiModel()
        val currentUiState = uiState.value
        val currentPokemonList = if (currentUiState is UiState.Loaded) {
            currentUiState.items
        } else {
            listOf()
        }
        currentOffset += PAGE_SIZE
        _uiState.emit(UiState.Loaded(currentPokemonList + pokemonsResult))

    }

    sealed interface UiState {
        data object Loading : UiState
        data class Loaded(val items: List<PokemonListUiModel>) : UiState
    }

    data class PokemonListUiModel(
        val pokemonKey: String,
        val pokedexId: String,
        val name: String,
        val imageUrl: String,
    )

    companion object {
        private const val PAGE_SIZE = 50
        private const val OFFSET_TO_START_FROM_POKEDEX_1 = 93
    }
}