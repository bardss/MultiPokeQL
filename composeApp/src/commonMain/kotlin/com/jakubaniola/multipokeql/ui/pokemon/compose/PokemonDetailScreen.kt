package com.jakubaniola.multipokeql.ui.pokemon.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.jakubaniola.multipokeql.designsystem.AppTheme
import com.jakubaniola.multipokeql.designsystem.ErrorScreen
import com.jakubaniola.multipokeql.designsystem.LoadingScreen
import com.jakubaniola.multipokeql.ui.pokemon.PokemonDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = koinViewModel()
) {
    AppTheme {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        when (uiState) {
            is PokemonDetailViewModel.UiState.Error -> ErrorScreen()
            is PokemonDetailViewModel.UiState.Loading -> LoadingScreen()
            is PokemonDetailViewModel.UiState.Loaded -> PokemonDetailContent(
                pokemonName = uiState.pokemon.pokemonName,
                pokedexNumber = uiState.pokemon.pokedexNumber,
                imageUrl = uiState.pokemon.imageUrl,
                height = uiState.pokemon.height,
                weight = uiState.pokemon.weight,
                isLegendary = uiState.pokemon.isLegendary,
                types = uiState.pokemon.types,
                gender = uiState.pokemon.gender,
                externalLink = uiState.pokemon.externalLink
            )
        }

    }
}

@Composable
fun PokemonDetailContent(
    pokemonName: String,
    pokedexNumber: String,
    imageUrl: String,
    height: String,
    weight: String,
    isLegendary: Boolean,
    types: List<String>,
    gender: String,
    externalLink: String
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .padding(16.dp)
        ) {
            // Image
            AsyncImage(
                model = imageUrl,
                contentDescription = "$pokemonName image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title and Subtitle
            Text(
                text = pokemonName,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "PokeDex #$pokedexNumber",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            // Height and Weight with Emojis
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "📏 Height: $height",
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "🏋️ Weight: $weight",
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider()

            // Legendary Status
            Text(
                text = if (isLegendary) "⭐ Legendary Pokémon" else "Regular Pokémon",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (isLegendary) MaterialTheme.colors.secondary else MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Types as Badges
            Text(
                text = "Types:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                types.forEach { type ->
                    Text(
                        text = type,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .background(MaterialTheme.colors.primary, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Gender with Emoji
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "🚻 Gender: $gender",
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // External Link Button
            Button(
                onClick = {
                    // Handle external link click
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text(
                    text = "More Information",
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}