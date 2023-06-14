package com.kt.startkit.ui.features.main.home.detail

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.kt.startkit.R
import com.kt.startkit.domain.entity.pokemon.PokemonAbility
import com.kt.startkit.domain.entity.pokemon.PokemonDetail
import com.kt.startkit.domain.entity.pokemon.PokemonStat
import com.kt.startkit.domain.entity.pokemon.PokemonStatType
import com.kt.startkit.domain.entity.pokemon.PokemonType
import com.kt.startkit.ui.res.IconResId
import com.kt.startkit.ui.util.Constants
import com.kt.startkit.ui.util.toFirstCharUpperCase

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    name: String?,
    onBackClick: () -> Unit,
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    when (state) {
        is PokemonDetailState.Initial -> {
            if (name != null) {
                viewModel.fetchPokemonDetail(name = name)
            }
            Text("initial")

        }

        is PokemonDetailState.Loading -> LoadingPokemonView()

        is PokemonDetailState.Error -> LoadingPokemonView()

        is PokemonDetailState.Data -> {
            PokemonDetailContentView(
                onBackClick = onBackClick,
                pokemon = (state as PokemonDetailState.Data).pokemon
            )
        }
    }
}

@Composable
private fun PokemonDetailContentView(
    onBackClick: () -> Unit,
    pokemon: PokemonDetail
) {


    Column(
        Modifier
            .background(color = pokemon.type.color.copy(alpha = 0.5f)),
    ) {
        PokemonDetailAppBar(onBackClick = onBackClick)

        // title
        Text(
            text = pokemon.name.toFirstCharUpperCase(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 23.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        // type
        PokemonTypeView(
            type = pokemon.type,
            modifier = Modifier.padding(horizontal = 23.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        // thumbnail
        PokemonThumbnailView(
            thumbnail = pokemon.thumbnail
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Sub
        PokemonSubview(pokemon = pokemon)
    }
}

@Composable
private fun PokemonThumbnailView(thumbnail: String?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = thumbnail,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f))
        )
    }

}

@Composable
private fun PokemonTypeView(type: PokemonType, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            type.toString(),
            fontSize = 17.sp,
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(alpha = 0.2f))
                .padding(horizontal = 10.dp, vertical = 5.dp)

        )
    }
    
}

@Composable
fun PokemonSubview(pokemon: PokemonDetail) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(Color.White)
            .verticalScroll(state = scrollState)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        // images
        PokemonImagesView(
            images = pokemon.images,
            color = pokemon.type.color
        )
        // stats
        PokemonStatsView(stats = pokemon.stats)

        Spacer(modifier = Modifier.height(20.dp))

        // type, height, weight
        PokemonInfoView(pokemon = pokemon)

        Spacer(modifier = Modifier.height(20.dp))

        // ability
        PokemonAbilityView(abilities = pokemon.ability)
    }
}

@Composable
private fun PokemonAbilityView(abilities: List<PokemonAbility>) {
    Column(Modifier.padding(horizontal = 23.dp)) {
        PokemonSubTitleView(title = stringResource(R.string.pokemon_detail_ability_title))
        Spacer(modifier = Modifier.height(10.dp))
        abilities.forEach {
            PokemonSubInfoView(
                text = it.name,
                color = if (it.isHidden) Color.LightGray else Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
private fun PokemonStatsView(stats: Map<PokemonStatType, PokemonStat>) {
    Column(modifier = Modifier.padding(horizontal = 23.dp)) {
        PokemonSubTitleView(title = stringResource(R.string.pokemon_detail_stat_title))
        Spacer(modifier = Modifier.height(10.dp))
        stats.entries.forEach {
            if (it.value.effort > 0) {
                PokemonSubInfoView(text = it.key.toString())
                Spacer(modifier = Modifier.height(5.dp))
                PokemonStatView(
                    effort = it.value.effort,
                    baseStat = it.value.baseStat,
                    color = it.key.color
                )
            }
        }
    }

}

@Composable
private fun PokemonStatView(
    effort: Int,
    baseStat: Int,
    color: Color
) {
    val restStat =
        if (effort > baseStat) {
            0
        } else {
            baseStat - effort
        }


    LazyRow( Modifier.fillMaxWidth()) {
        items(effort) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(30.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(color)
                    .padding(horizontal = 5.dp)
            )
        }

        items(restStat) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(30.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(Color.LightGray.copy(alpha = 0.7f))
                    .padding(horizontal = 5.dp)
            )
        }
    }
}

@Composable
private fun PokemonSubTitleView(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun PokemonSubInfoView(text: String, color: Color = Color.Black) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = color
    )
}

@Composable
private fun PokemonInfoView(pokemon: PokemonDetail) {
    Column(
        modifier = Modifier.padding(horizontal = 23.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PokemonSubTitleView(title = stringResource(R.string.pokemon_detail_height_title))
        PokemonSubInfoView(pokemon.height.toString())

        Spacer(modifier = Modifier.height(5.dp))

        PokemonSubTitleView(title = stringResource(R.string.pokemon_detail_weight_title))
        PokemonSubInfoView(pokemon.weight.toString())
    }
}

@Composable
private fun PokemonImagesView(images: List<String>, color: Color) {
    LazyRow(contentPadding = PaddingValues(horizontal = 30.dp)) {
        items(images) {
            AsyncImage(
                model = it,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color.copy(alpha = 0.1f))
            )
        }
    }
}

@Composable
private fun PokemonDetailAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
//            .background(color.copy(alpha = 0.5f))
        ,
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = IconResId.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }

        IconButton(
            onClick = {
                // do something
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.FavoriteBorder,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
private fun LoadingPokemonView() {
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
        modifier = Modifier.padding(20.dp)
    ) {
        Spacer(Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray.copy(alpha = alpha)),
        )

        Spacer(Modifier.height(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(alpha = alpha)),
            )
        }

        Spacer(Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .height(40.dp)
                .width(350.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray.copy(alpha = alpha)),
        )

        Spacer(Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .height(40.dp)
                .width(350.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray.copy(alpha = alpha)),
        )
    }
}

@Preview
@Composable
fun Preview() {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray),
        )

        Spacer(Modifier.height(30.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
            )
        }

        Spacer(Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .height(40.dp)
                .width(400.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray),
        )

        Spacer(Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .height(40.dp)
                .width(400.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray),
        )
    }
}