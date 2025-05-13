package com.jakubaniola.multipokeql.ui.home.compose

import androidx.compose.foundation.lazy.LazyListState

private const val NUM_OF_ITEMS_BEFORE_LOAD = 10

fun shouldLoadNextPage(
    listState: LazyListState
): Boolean {
    val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
    val totalItemCount = listState.layoutInfo.totalItemsCount
    return totalItemCount != 0 && lastVisibleIndex >= (totalItemCount - NUM_OF_ITEMS_BEFORE_LOAD)
}