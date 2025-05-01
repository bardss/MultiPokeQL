package com.jakubaniola.multipokeql.data

class PokemonRepositoryImpl(
    private val pokemonRemoteService: PokemonRemoteServiceImpl
) : PokemonRepository {
    override suspend fun getPokemons() = pokemonRemoteService.getPokemons()
}