package com.jakubaniola.multipokeql.data

interface PokemonRepository {
    suspend fun getPokemons(): List<RemotePokemonListItem>
}

data class RemotePokemonListItem(
    val pokedexNum: String,
    val name: String,
    val imageUrl: String,
)