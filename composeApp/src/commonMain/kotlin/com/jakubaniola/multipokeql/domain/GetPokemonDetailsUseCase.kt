package com.jakubaniola.multipokeql.domain

import com.jakubaniola.multipokeql.core.mapSuccess

class GetPokemonDetailsUseCase(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(pokemonKey: String) = pokemonRepository
        .getPokemon(pokemonKey)
        .mapSuccess { it.toPokemon() }
}

data class Gender(
    val male: String,
    val female: String
)

data class Pokemon(
    val pokemonName: String,
    val pokedexNumber: String,
    val imageUrl: String,
    val height: String,
    val weight: String,
    val isLegendary: Boolean,
    val types: List<String>,
    val gender: Gender,
    val externalLink: String,
)

fun RemotePokemon.toPokemon() = Pokemon(
    pokemonName = this.pokemonName,
    pokedexNumber = this.pokedexNumber,
    imageUrl = this.imageUrl,
    height = this.height,
    weight = this.weight,
    isLegendary = this.isLegendary,
    types = this.types,
    gender = Gender(
        male = this.gender.male,
        female = this.gender.female
    ),
    externalLink = this.externalLink
)
