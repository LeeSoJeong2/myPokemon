package com.kt.startkit.ui.features.main.home

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import coil.compose.AsyncImage
import com.kt.startkit.R
import com.kt.startkit.domain.entity.pokemon.Pokemon
import com.kt.startkit.ui.util.Constants
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
//    val state by viewModel.state.collectAsState()

    when (state) {
        is HomeState.Initial -> {
            viewModel.observePokemonInfo()
            viewModel.observePokemonMap()
        }

        is HomeState.Updating -> {
            HomeContentView(
                pokemonList = emptyList(),
                pageCount = 0,
                currentPage = 0
            )
        }

        is HomeState.Data -> {
            viewModel.fetchPokemon((state as HomeState.Data).pokemonInfo.names)
            
            val homeState = (state as HomeState.Data)

            HomeContentView(
                pokemonList = emptyList(),
                pageCount = (homeState.pokemonInfo.count / Constants.pageOffset),
                currentPage = homeState.currentPage
            )
        }

        is HomeState.Updated -> {
            val homeState = (state as HomeState.Updated)
            HomeContentView(
                pokemonList = homeState.pokemonList,
                pageCount = homeState.totalCount / Constants.pageOffset,
                currentPage = homeState.currentPage
            )
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
    pageCount: Int,
    currentPage: Int,
) {
    LocalViewModelStoreOwner.current

    val dummy = Constants.pageOffset - pokemonList.size

    Box(contentAlignment = Alignment.BottomCenter) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(pokemonList) {
                PokemonCell(pokemon = it)
            }

            if (dummy > 0 ) {
                items(dummy) {
                    LoadingPokemonCell()
                }
            }
        }
        PokemonPager(
            modifier = Modifier.padding(horizontal = 30.dp),
            pageCount = pageCount,
            currentPage = currentPage)
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
        Text(
            pokemon.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
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

@Composable
private fun PokemonPager(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier,
    pageCount: Int,
    currentPage: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.8f)),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            stringResource(R.string.home_screen_previous_page),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(10.dp)
                .clickable {
                    if (currentPage > 0) {
                        viewModel.fetchPokemonInfo(currentPage - 1)
                    }
                }
        )
        Spacer(modifier = Modifier.width(50.dp))
        Text(
            stringResource(R.string.home_screen_next_page),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(10.dp)
                .clickable {
                    if (currentPage < pageCount) {
                        viewModel.fetchPokemonInfo(currentPage + 1)
                    }
                }
        )
    }
}
