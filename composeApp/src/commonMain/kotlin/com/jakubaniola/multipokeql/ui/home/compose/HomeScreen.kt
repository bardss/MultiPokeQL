package com.jakubaniola.multipokeql.ui.home.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jakubaniola.multipokeql.designsystem.AppScaffold
import com.jakubaniola.multipokeql.designsystem.AppTheme
import com.jakubaniola.multipokeql.designsystem.LoadingScreen
import com.jakubaniola.multipokeql.ui.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    listState: LazyListState,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    navigateToDetails: (String) -> Unit,
) {
    AppTheme {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        HomeContent(
            uiState = uiState,
            listState = listState,
            navigateToDetails = navigateToDetails,
            loadNextPage = viewModel::loadNextPage
        )
    }
}

@Composable
fun HomeContent(
    uiState: HomeViewModel.UiState,
    listState: LazyListState,
    navigateToDetails: (String) -> Unit,
    loadNextPage: () -> Unit,
) {
    AppScaffold(
        title = "Pokédex",
    ) { paddingValues ->
        when (uiState) {
            is HomeViewModel.UiState.Loaded -> HomeList(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                listState = listState,
                navigateToDetails = navigateToDetails,
                loadNextPage = loadNextPage,
            )

            is HomeViewModel.UiState.Loading -> LoadingScreen()
        }
    }
}

@Composable
fun HomeList(
    modifier: Modifier,
    uiState: HomeViewModel.UiState.Loaded,
    listState: LazyListState,
    navigateToDetails: (String) -> Unit,
    loadNextPage: () -> Unit,
) {
    val shouldLoadNextPage by remember {
        derivedStateOf { shouldLoadNextPage(listState) }
    }
    if (shouldLoadNextPage) {
        LaunchedEffect(listState) { loadNextPage() }
    }
    LazyColumn(
        modifier = modifier,
        state = listState,
    ) {
        items(uiState.items.size) { index ->
            val item = uiState.items[index]
            PokemonListEntry(
                modifier = Modifier.clickable {
                    navigateToDetails(item.pokemonKey)
                },
                pokemonName = item.name,
                pokedexNumber = item.pokedexId.toInt(),
                imageUrl = item.imageUrl
            )
        }
    }
}