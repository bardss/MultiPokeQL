package com.jakubaniola.multipokeql.data

import com.jakubaniola.multipokeql.core.ServiceResponse
import com.jakubaniola.multipokeql.domain.RemotePokemon
import com.jakubaniola.multipokeql.domain.RemotePokemonListItem

interface PokemonRemoteService {
    suspend fun getPokemons(offset: Int, pageSize: Int): ServiceResponse<List<RemotePokemonListItem>>
    suspend fun getPokemon(pokemonKey: String): ServiceResponse<RemotePokemon>
}