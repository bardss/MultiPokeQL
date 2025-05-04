package com.jakubaniola.multipokeql.data

import com.jakubaniola.multipokeql.core.ServiceResponse

interface PokemonRemoteService {
    suspend fun getPokemons(): ServiceResponse<List<RemotePokemonListItem>>
    suspend fun getPokemon(pokemonKey: String): ServiceResponse<RemotePokemon>
}