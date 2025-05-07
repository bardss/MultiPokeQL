package com.jakubaniola.multipokeql.data

import com.apollographql.apollo.ApolloClient
import com.jakubaniola.multipokeql.GetPokemonDetailsQuery
import com.jakubaniola.multipokeql.GetPokemonListQuery
import com.jakubaniola.multipokeql.core.ServiceResponse
import com.jakubaniola.multipokeql.type.PokemonEnum

class PokemonRemoteServiceImpl : PokemonRemoteService {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl(POKEMON_GRAPHQL_URL)
        .build()

    override suspend fun getPokemons() = ServiceResponse.Success(
        apolloClient
            .query(GetPokemonListQuery(OFFSET_TO_START_FROM_POKEDEX_1, 200))
            .execute()
            .data
            ?.getAllPokemon
            ?.toRemotePokemonListItem() ?: listOf()
    )

    override suspend fun getPokemon(pokemonKey: String): ServiceResponse<RemotePokemon> {
        val response = apolloClient
            .query(GetPokemonDetailsQuery(PokemonEnum.safeValueOf(pokemonKey)))
            .execute()
            .data
            ?.getPokemon
            ?.toRemotePokemon()
        return if (response != null) {
            ServiceResponse.Success(response)
        } else {
            ServiceResponse.Error()
        }
    }

    companion object {
        private const val POKEMON_GRAPHQL_URL = "https://graphqlpokemon.favware.tech/v8"
        private const val OFFSET_TO_START_FROM_POKEDEX_1 = 93
    }
}

private fun List<GetPokemonListQuery.GetAllPokemon>.toRemotePokemonListItem() =
    map { it.toRemotePokemonListItem() }

private fun GetPokemonListQuery.GetAllPokemon.toRemotePokemonListItem() = RemotePokemonListItem(
    pokemonKey = key.rawValue,
    pokedexNumber = num.toString(),
    name = species,
    imageUrl = sprite,
)

private fun GetPokemonDetailsQuery.GetPokemon.toRemotePokemon() = RemotePokemon(
    pokemonName = species,
    pokedexNumber = num.toString(),
    imageUrl = sprite,
    height = height.toString(),
    weight = weight.toString(),
    isLegendary = legendary,
    gender = gender.toString(),
    externalLink = bulbapediaPage,
    types = types.map { it.name }
)