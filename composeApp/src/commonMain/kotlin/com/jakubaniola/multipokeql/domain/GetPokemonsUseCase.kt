package com.jakubaniola.multipokeql.domain

class GetPokemonsUseCase(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(offset: Int, pageSize: Int) = pokemonRepository
        .getPokemons(offset, pageSize)
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