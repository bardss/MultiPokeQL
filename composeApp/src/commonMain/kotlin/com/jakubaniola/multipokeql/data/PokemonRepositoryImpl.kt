package com.jakubaniola.multipokeql.data

import com.jakubaniola.multipokeql.core.DataResult
import com.jakubaniola.multipokeql.core.toDataResult

class PokemonRepositoryImpl(
    private val pokemonRemoteService: PokemonRemoteServiceImpl
) : PokemonRepository {
    override suspend fun getPokemons() =
        DataResult.Success(
            pokemonRemoteService
                .getPokemons()
                .data
                .distinctBy { it.pokedexNumber }
        )

    override suspend fun getPokemon(key: String) =
        pokemonRemoteService
            .getPokemon(key)
            .toDataResult()
}