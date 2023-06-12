package com.kt.startkit.ui.features.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.kt.startkit.domain.entity.Item
import com.kt.startkit.domain.entity.Pokemon

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
//    val state by viewModel.state.collectAsState()

    when (state) {
        is HomeState.Initial -> {
            viewModel.observeUserProfile()
        }
        is HomeState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is HomeState.Data -> {
            HomeContentView((state as HomeState.Data).pokemonInfo.results)
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
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList) {
            PokemonIcon(pokemon = it)
        }
    }
}

@Composable
private fun PokemonIcon(pokemon: Pokemon) {
    Column {
        Text(pokemon.name)
    }
}

@Composable
private fun HomeItemView(item: Item) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = item.price.toString() + "원")
    }
}

@Preview
@Composable
fun HomeScreenPeView() {
//    HomeScreen()
    PokemonIcon(pokemon = Pokemon(name = "Pokemon", id = "1"))
}
