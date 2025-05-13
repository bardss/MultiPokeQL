package com.jakubaniola.multipokeql.data

import com.jakubaniola.multipokeql.core.DataResult
import com.jakubaniola.multipokeql.core.toDataResult
import com.jakubaniola.multipokeql.domain.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonRemoteService: PokemonRemoteServiceImpl
) : PokemonRepository {
    override suspend fun getPokemons(offset: Int, pageSize: Int) =
        DataResult.Success(
            pokemonRemoteService
                .getPokemons(offset, pageSize)
                .data
        )

    override suspend fun getPokemon(pokemonKey: String) =
        pokemonRemoteService
            .getPokemon(pokemonKey)
            .toDataResult()
}