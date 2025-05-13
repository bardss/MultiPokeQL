package com.jakubaniola.multipokeql.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import multipokeql.composeapp.generated.resources.Res
import multipokeql.composeapp.generated.resources.pokemon_placeholder
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageByUrl(
    modifier: Modifier,
    imageUrl: String,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        placeholder = painterResource(Res.drawable.pokemon_placeholder),
        error = painterResource(Res.drawable.pokemon_placeholder),
        modifier = modifier
    )
}