package com.jakubaniola.multipokeql.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.multipokeql.core.DataResult
import com.jakubaniola.multipokeql.domain.GetPokemonDetailsUseCase
import com.jakubaniola.multipokeql.domain.Pokemon
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class PokemonDetailsViewModel(
    private val pokemonKey: String,
    private val getPokemonDetails: GetPokemonDetailsUseCase,
) : ViewModel() {
    val uiState: StateFlow<UiState> = flow {
        emit(getInitialUiState())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState.Loading)

    private suspend fun getInitialUiState() =
        when (val result = getPokemonDetails(pokemonKey)) {
            is DataResult.Error<*> -> UiState.Error
            is DataResult.Success<Pokemon> ->
                UiState.Loaded(result.data.toPokemon())
        }

    private fun Pokemon.toPokemon() = PokemonUiModel(
        pokemonName = this.pokemonName,
        pokedexNumber = this.pokedexNumber,
        imageUrl = this.imageUrl,
        height = this.height,
        weight = this.weight,
        isLegendary = this.isLegendary,
        types = this.types,
        gender = this.gender,
        externalLink = this.externalLink
    )

    sealed interface UiState {
        data object Loading : UiState
        data object Error : UiState
        data class Loaded(val pokemon: PokemonUiModel) : UiState
    }

    data class PokemonUiModel(
        val pokemonName: String,
        val pokedexNumber: String,
        val imageUrl: String,
        val height: String,
        val weight: String,
        val isLegendary: Boolean,
        val types: List<String>,
        val gender: String,
        val externalLink: String,
    )
}