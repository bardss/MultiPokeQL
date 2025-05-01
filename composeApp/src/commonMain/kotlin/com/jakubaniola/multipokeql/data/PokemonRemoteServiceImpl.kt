package com.jakubaniola.multipokeql.data

import com.apollographql.apollo.ApolloClient
import com.jakubaniola.multipokeql.GetPokemonListQuery

class PokemonRemoteServiceImpl : PokemonRemoteService {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl(POKEMON_GRAPHQL_URL)
        .build()

    override suspend fun getPokemons() = apolloClient
        .query(GetPokemonListQuery(OFFSET_TO_START_FROM_POKEDEX_1, 200))
        .execute()
        .data
        ?.getAllPokemon
        ?.toRemotePokemonListItem() ?: listOf()

    companion object {
        private const val POKEMON_GRAPHQL_URL = "https://graphqlpokemon.favware.tech/v8"
        private const val OFFSET_TO_START_FROM_POKEDEX_1 = 93
    }
}

private fun List<GetPokemonListQuery.GetAllPokemon>.toRemotePokemonListItem() =
    map { it.toRemotePokemonListItem() }

private fun GetPokemonListQuery.GetAllPokemon.toRemotePokemonListItem() = RemotePokemonListItem(
    pokedexNum = num.toString(),
    name = species,
    imageUrl = sprite
)