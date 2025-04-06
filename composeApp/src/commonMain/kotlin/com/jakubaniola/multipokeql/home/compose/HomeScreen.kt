package com.jakubaniola.multipokeql.home.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jakubaniola.multipokeql.home.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    MaterialTheme {
        Text(viewModel.greeting)
    }
}