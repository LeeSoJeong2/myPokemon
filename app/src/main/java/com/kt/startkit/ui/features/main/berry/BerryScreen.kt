package com.kt.startkit.ui.features.main.berry

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import coil.compose.AsyncImage
import com.kt.startkit.domain.entity.Berry
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.features.main.root.NavigationRoute
import com.kt.startkit.ui.features.main.root.navigateToBerryItem
import com.kt.startkit.ui.features.main.root.navigateToSettingItem

@Composable
fun BerryScreen(
    onItemClick: (String) -> Unit,
    viewModel: BerryScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
//    val state by viewModel.state.collectAsState()

    when (state) {
        is BerryViewState.Initial -> {
            viewModel.fetchInitialData()
        }
        is BerryViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is BerryViewState.Data -> {
            BerryContentView((state as BerryViewState.Data).berries)
        }

        is BerryViewState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    (state as BerryViewState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}

@Composable
private fun BerryContentView(
    berries: List<Berry>,
) {
    LocalViewModelStoreOwner.current

    val navController = LocalNavigationProvider.current

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(all = 8.dp),

    ) {
        items(berries) { berry ->
            BerryItemView(
                berry = berry,
                onClick = {
                    navController.navigateToBerryItem(NavigationRoute.BERRY_DETAIL.routeName)
                }
            )
        }
    }
}

@Composable
private fun BerryItemView(
    berry: Berry,
    onClick: () -> Unit,
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
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = berry.imageUrl,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = berry.name,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


