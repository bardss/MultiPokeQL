package com.jakubaniola.multipokeql.data

import com.jakubaniola.multipokeql.core.DataResult

interface PokemonRepository {
    suspend fun getPokemons(): DataResult.Success<List<RemotePokemonListItem>>
    suspend fun getPokemon(pokemonKey: String): DataResult<RemotePokemon>
}

data class RemotePokemonListItem(
    val pokemonKey: String,
    val pokedexNumber: String,
    val name: String,
    val imageUrl: String,
)

data class RemoteGender(
    val male: String,
    val female: String
)

data class RemotePokemon(
    val pokemonName: String,
    val pokedexNumber: String,
    val imageUrl: String,
    val height: String,
    val weight: String,
    val isLegendary: Boolean,
    val types: List<String>,
    val gender: RemoteGender,
    val externalLink: String,
)
