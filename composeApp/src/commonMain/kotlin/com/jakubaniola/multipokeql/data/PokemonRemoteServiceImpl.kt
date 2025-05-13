package com.jakubaniola.multipokeql.data

import com.apollographql.apollo.ApolloClient
import com.jakubaniola.multipokeql.GetPokemonDetailsQuery
import com.jakubaniola.multipokeql.GetPokemonListQuery
import com.jakubaniola.multipokeql.core.ServiceResponse
import com.jakubaniola.multipokeql.domain.RemoteGender
import com.jakubaniola.multipokeql.domain.RemotePokemon
import com.jakubaniola.multipokeql.domain.RemotePokemonListItem
import com.jakubaniola.multipokeql.type.PokemonEnum

class PokemonRemoteServiceImpl : PokemonRemoteService {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl(POKEMON_GRAPHQL_URL)
        .build()

    override suspend fun getPokemons(offset: Int, pageSize: Int) = ServiceResponse.Success(
        apolloClient
            .query(GetPokemonListQuery(offset, pageSize))
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
    gender = RemoteGender(
        male = gender.male,
        female = gender.female
    ),
    externalLink = bulbapediaPage,
    types = types.map { it.name }
)