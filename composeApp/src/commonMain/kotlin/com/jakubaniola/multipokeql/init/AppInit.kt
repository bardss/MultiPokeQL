package com.jakubaniola.multipokeql.init

import com.jakubaniola.multipokeql.data.PokemonRemoteService
import com.jakubaniola.multipokeql.data.PokemonRemoteServiceImpl
import com.jakubaniola.multipokeql.data.PokemonRepository
import com.jakubaniola.multipokeql.data.PokemonRepositoryImpl
import com.jakubaniola.multipokeql.domain.GetPokemonDetailsUseCase
import com.jakubaniola.multipokeql.domain.GetPokemonsUseCase
import com.jakubaniola.multipokeql.ui.home.HomeViewModel
import com.jakubaniola.multipokeql.ui.pokemon.PokemonDetailsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        val appModule = module {
            single<GetPokemonsUseCase> { GetPokemonsUseCase(get()) }
            single<GetPokemonDetailsUseCase> { GetPokemonDetailsUseCase(get()) }
            single<PokemonRepository> { PokemonRepositoryImpl(get()) }
            single<PokemonRemoteService> { PokemonRemoteServiceImpl() }
            single { PokemonRemoteServiceImpl() }
        }
        val viewModelModule = module {
            viewModelOf(::HomeViewModel)
            viewModel { (pokemonKey: String) ->
                PokemonDetailsViewModel(pokemonKey, get())
            }
        }
        modules(viewModelModule, appModule)
    }
}
