package com.jakubaniola.multipokeql.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakubaniola.multipokeql.core.DataResult
import com.jakubaniola.multipokeql.domain.GetPokemonDetailsUseCase
import com.jakubaniola.multipokeql.domain.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(
    private val getPokemonDetails: GetPokemonDetailsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun resetUiState() {
        _uiState.value = UiState.Loading
    }

    fun initUiState(pokemonKey: String) = viewModelScope.launch {
        _uiState.emit(UiState.Loading)
        val uiState = when (val result = getPokemonDetails(pokemonKey)) {
            is DataResult.Error<*> -> UiState.Error
            is DataResult.Success<Pokemon> ->
                UiState.Loaded(result.data.toPokemon())
        }
        _uiState.emit(uiState)
    }

    private fun Pokemon.toPokemon() = PokemonUiModel(
        pokemonName = this.pokemonName.capitalize(),
        pokedexNumber = this.pokedexNumber,
        imageUrl = this.imageUrl,
        height = this.height + " m",
        weight = this.weight + " kg",
        isLegendary = this.isLegendary,
        types = this.types.map { PokemonType.fromString(it) },
        genderMale = this.gender.male,
        genderFemale = this.gender.female,
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
        val types: List<PokemonType>,
        val genderMale: String,
        val genderFemale: String,
        val externalLink: String,
    )

    enum class PokemonType(val color: Long) {
        BUG(0xFFA8B820),
        DARK(0xFF705848),
        DRAGON(0xFF7038F8),
        ELECTRIC(0xFFF8D030),
        FAIRY(0xFFEE99AC),
        FIGHTING(0xFFC03028),
        FIRE(0xFFF08030),
        FLYING(0xFFA890F0),
        GHOST(0xFF705898),
        GRASS(0xFF78C850),
        GROUND(0xFFE0C068),
        ICE(0xFF98D8D8),
        NORMAL(0xFFA8A878),
        POISON(0xFFA040A0),
        PSYCHIC(0xFFF85888),
        ROCK(0xFFB8A038),
        STEEL(0xFFB8B8D0),
        WATER(0xFF6890F0);

        companion object {
            fun fromString(value: String) =
                values().firstOrNull { it.name.equals(value, ignoreCase = true) } ?: NORMAL
        }
    }
}