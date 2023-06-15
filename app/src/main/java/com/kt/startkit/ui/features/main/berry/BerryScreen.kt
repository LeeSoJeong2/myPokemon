package com.kt.startkit.ui.features.main.berry

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.kt.startkit.domain.entity.berry.BerryInfo
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.features.main.root.NavigationRoute
import com.kt.startkit.ui.features.main.root.navigateToBerryItem

@Composable
fun BerryScreen(
    onBerryClick: (String) -> Unit,
    viewModel: BerryScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    when (state) {
        is BerryState.Initial -> {
            viewModel.fetchInitialData()
        }

        is BerryState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is BerryState.Data -> {
            BerryContentView(
                onBerryClick = onBerryClick,
                berryInfo = (state as BerryState.Data).berryInfo
            )
        }

        is BerryState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    (state as BerryState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}

@Composable
private fun BerryContentView(
    onBerryClick: (String) -> Unit = {},
    berryInfo: BerryInfo
) {
    LocalViewModelStoreOwner.current

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(all = 8.dp),

    ) {
        itemsIndexed(berryInfo.berryNames) { index, berryName ->
            BerryItemView(
                berryName = berryName,
                onBerryClick = onBerryClick
            )
        }
    }
}

@Composable
private fun BerryItemView(
    onBerryClick: (String) -> Unit = {},
    berryName: String
) {
    Box(
        modifier = Modifier
            .padding(
                vertical = 18.dp,
                horizontal = 3.dp
            )
            .border(
                width = 1.dp,
                color = Color.Gray,
            )
            .clickable { onBerryClick(berryName) },
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = berryName,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


