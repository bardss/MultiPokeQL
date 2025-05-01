package com.jakubaniola.multipokeql.data

interface PokemonRemoteService {
    suspend fun getPokemons(): List<RemotePokemonListItem>
}