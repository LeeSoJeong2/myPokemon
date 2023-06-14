package com.kt.startkit.ui.features.main.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kt.startkit.ui.res.IconResId

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: FavoriteScreenViewModel = hiltViewModel(),
) {
    Column {
        FavoriteAppBar(onBackClick = onBackClick)

        val state by viewModel.viewState.collectAsStateWithLifecycle()

        when (state) {
            is FavoriteViewState.Initial -> {
                viewModel.observeUserProfile()
            }

            is FavoriteViewState.Loading -> {

            }

            is FavoriteViewState.Data -> {
                //
            }

            is FavoriteViewState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        (state as FavoriteViewState.Error).message,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

}


@Composable
fun FavoriteAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = IconResId.ArrowBack,
                contentDescription = null,
            )
        }
        Text("favorite")
    }
}


