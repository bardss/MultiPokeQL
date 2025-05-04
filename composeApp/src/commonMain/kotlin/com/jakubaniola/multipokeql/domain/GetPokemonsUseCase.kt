package com.jakubaniola.multipokeql.domain

import com.jakubaniola.multipokeql.data.PokemonRepository
import com.jakubaniola.multipokeql.data.RemotePokemonListItem

class GetPokemonsUseCase(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke() = pokemonRepository
        .getPokemons()
        .data
        .toPokemonListItem()
}

private fun List<RemotePokemonListItem>.toPokemonListItem() =
    map { it.toPokemonListItem() }

private fun RemotePokemonListItem.toPokemonListItem() = PokemonListItem(
    pokemonKey = pokemonKey,
    pokedexNum = pokedexNumber,
    name = name,
    imageUrl = imageUrl
)

data class PokemonListItem(
    val pokemonKey: String,
    val pokedexNum: String,
    val name: String,
    val imageUrl: String,
)