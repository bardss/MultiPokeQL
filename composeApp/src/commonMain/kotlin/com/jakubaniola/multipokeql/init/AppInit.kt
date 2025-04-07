package com.jakubaniola.multipokeql.init

import com.jakubaniola.multipokeql.ui.home.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf

fun initKoin() {
    startKoin {
        val viewModelModule = module {
            viewModelOf(::HomeViewModel)
        }
        modules(viewModelModule)
    }
}
