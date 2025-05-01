package com.jakubaniola.multipokeql.data

import com.apollographql.apollo.ApolloClient
import com.jakubaniola.multipokeql.GetPokemonListQuery

class PokemonRemoteServiceImpl : PokemonRemoteService {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://graphqlpokemon.favware.tech/v8")
        .build()

    override suspend fun getPokemons() = apolloClient
        .query(GetPokemonListQuery(93, 50))
        .execute()
        .data
        ?.getAllPokemon
        ?.toRemotePokemonListItem() ?: listOf()
}

private fun List<GetPokemonListQuery.GetAllPokemon>.toRemotePokemonListItem() =
    map { it.toRemotePokemonListItem() }

private fun GetPokemonListQuery.GetAllPokemon.toRemotePokemonListItem() = RemotePokemonListItem(
    pokedexNum = num.toString(),
    name = species,
    imageUrl = sprite
)