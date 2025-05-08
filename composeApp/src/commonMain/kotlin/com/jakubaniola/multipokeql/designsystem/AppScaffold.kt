package com.jakubaniola.multipokeql.designsystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AppScaffold(
    title: String,
    onBackClick: (() -> Unit)? = null,
    content: @Composable (paddingValues: PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                content = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (onBackClick != null) {
                            IconButton(
                                modifier = Modifier.align(Alignment.CenterStart),
                                onClick = onBackClick,
                            ) {
                                Text(
                                    text = "⬅️",
                                    fontSize = 24.sp
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = title,
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onPrimary,
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues -> content(paddingValues) }
}