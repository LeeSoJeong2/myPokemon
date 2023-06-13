package com.kt.startkit.ui.features.main.home

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import coil.compose.AsyncImage
import com.kt.startkit.domain.entity.pokemon.Pokemon

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
//    val state by viewModel.state.collectAsState()

    when (state) {
        is HomeState.Initial -> {
            viewModel.observePokemonInfo()
        }

        is HomeState.Data -> {
            viewModel.fetchPokemon((state as HomeState.Data).pokemonInfo.names)
            viewModel.observePokemonMap()
            HomeContentView(emptyList())
        }

        is HomeState.Updated -> {
            HomeContentView(pokemonList = (state as HomeState.Updated).pokemonList)
        }

        is HomeState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    (state as HomeState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun HomeContentView(
    pokemonList: List<Pokemon>,
) {
    LocalViewModelStoreOwner.current

    val dummy = 10 - pokemonList.size

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(pokemonList) {
            PokemonCell(pokemon = it)
        }

        items(dummy) {
            LoadingPokemonCell()
        }
    }
}

@Composable
private fun PokemonCell(pokemon: Pokemon) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        AsyncImage(
            model = pokemon.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(pokemon.type.color.copy(alpha = 0.5f))
            )
        Text(pokemon.name)
    }
}

@Composable
private fun LoadingPokemonCell() {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                0.7f at 500
            },
            repeatMode = RepeatMode.Reverse
        )
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray.copy(alpha = alpha)),
        )
    }

}

