package com.kt.startkit.ui.features.main.home

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import coil.compose.AsyncImage
import com.kt.startkit.R
import com.kt.startkit.domain.entity.pokemon.Pokemon
import com.kt.startkit.domain.entity.pokemon.PokemonType
import com.kt.startkit.ui.theme.CustomColor
import com.kt.startkit.ui.util.Constants
import com.kt.startkit.ui.util.toFirstCharUpperCase

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPokemonClick: (String) -> Unit = {}
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    when (state) {
        is HomeState.Initial -> {
            viewModel.observePokemonInfo()
            viewModel.observePokemonList()
        }

        is HomeState.Fetching -> {
            // Shimmer 화면.
            HomeContentView(
                pokemonList = emptyList(),
                pageCount = 0,
                currentPage = 0,
                onPokemonClick = onPokemonClick,
                isFetching = true
            )
        }

        is HomeState.PokemonInfoFetched -> {
            viewModel.fetchPokemon((state as HomeState.PokemonInfoFetched).pokemonInfo.names)
            
            val homeState = (state as HomeState.PokemonInfoFetched)

            HomeContentView(
                pokemonList = emptyList(),
                pageCount = (homeState.pokemonInfo.count / Constants.PAGE_OFFSET),
                currentPage = homeState.currentPage,
                onPokemonClick = onPokemonClick,
            )
        }

        is HomeState.PokemonListFetched -> {
            val homeState = (state as HomeState.PokemonListFetched)
            HomeContentView(
                pokemonList = homeState.pokemonList,
                pageCount = homeState.totalCount / Constants.PAGE_OFFSET,
                currentPage = homeState.currentPage,
                onPokemonClick = onPokemonClick,
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
    onPokemonClick: (String) -> Unit = {},
    isFetching: Boolean = false,
) {
    val placeholder = Constants.PAGE_OFFSET - pokemonList.size

    Box(contentAlignment = Alignment.BottomCenter) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(pokemonList) {
                PokemonCell(
                    pokemon = it,
                    onPokemonClick = onPokemonClick
                )
            }

            if (placeholder > 0 ) {
                items(placeholder) {
                    LoadingPokemonCell()
                }
            }

            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

        if (!isFetching) {
            PokemonPager(
                pageCount = pageCount,
                currentPage = currentPage)
        }
    }
}

@Composable
private fun PokemonCell(
    pokemon: Pokemon,
    onPokemonClick: (String) -> Unit = {}
) {
    val modifier = Modifier
        .size(140.dp)
        .clip(RoundedCornerShape(20.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onPokemonClick(pokemon.name)
            }
    ) {
        Box(
            modifier = modifier
                .background(pokemon.type.color.copy(alpha = 0.5f))
        ) {
            PokemonInfoView(modifier = modifier, pokemon = pokemon)

            // Pokemon Image
            Box(
                modifier = modifier.padding(5.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                AsyncImage(
                    model = pokemon.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                )
            }
        }
    }
}

@Composable
private fun PokemonInfoView(
    modifier: Modifier,
    pokemon: Pokemon
) {
    Box(
        modifier = modifier
            .padding(start = 10.dp, top = 10.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column {
            // Pokemon Name
            Text(
                pokemon.name.toFirstCharUpperCase(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Pokemon Type
            Text(
                pokemon.type.toString(),
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.White.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 5.dp)
            )
        }
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
                .size(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray.copy(alpha = alpha)),
        )
    }
}

@Composable
private fun PokemonPager(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    pageCount: Int,
    currentPage: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.8f))
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PageButton(
            text = stringResource(R.string.home_screen_previous_page),
            icon = Icons.Rounded.ArrowBack,
            color = CustomColor.Red.copy(alpha = 0.8f),
            onClick = {
                if (currentPage > 0) {
                    viewModel.fetchPokemonInfo(currentPage - 1)
                }
            }
        )
        PageButton(
            text = stringResource(R.string.home_screen_next_page),
            icon = Icons.Rounded.ArrowForward,
            color = CustomColor.Blue.copy(alpha = 0.8f),
            onClick = {
                if (currentPage < pageCount) {
                    viewModel.fetchPokemonInfo(currentPage + 1)
                }
            }
        )
    }
}

@Composable
private fun PageButton(
    text: String,
    icon: ImageVector = Icons.Rounded.ArrowBack,
    color: Color = Color.LightGray,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .clickable { onClick() }
            .background(color)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .padding(5.dp),
            imageVector = icon,
            contentDescription = null,
            tint = color
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}
