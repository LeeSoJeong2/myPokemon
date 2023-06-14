package com.kt.startkit.ui.features.main.setting

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
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
import com.kt.startkit.domain.entity.pokemon.Pokemon
import com.kt.startkit.domain.entity.pokemon.PokemonType
import com.kt.startkit.ui.res.IconResId
import com.kt.startkit.ui.util.toFirstCharUpperCase

@Composable
fun SettingScreen(
    onBackClick: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    when (state) {
        is SettingState.Initial -> {
            viewModel.fetchProfile()
        }

        is SettingState.Loading -> SettingLoadingView()

        is SettingState.Error -> {
            SettingContentScreen(
                onBackClick = onBackClick,
                pokemon = null
            )
        }

        is SettingState.Data -> {
            SettingContentScreen(
                onBackClick = onBackClick,
                pokemon = (state as SettingState.Data).pokemon
            )
        }
    }
}

@Composable
private fun SettingContentScreen(
    onBackClick: () -> Unit,
    pokemon: Pokemon?
) {
    Column {
        SettingAppBar (
            onBackClick = onBackClick
        )
        SettingContentView(pokemon = pokemon)
    }

}

@Composable
private fun SettingAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = IconResId.ArrowBack,
                contentDescription = null,
            )
        }
        Text(
            stringResource(R.string.setting_title),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun SettingContentView(
    modifier: Modifier = Modifier,
    pokemon: Pokemon?
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .background(Color(0xFFF2F2F2))
            .padding(23.dp)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 프로필
        if (pokemon != null) {
            ProfileView(pokemon = pokemon)
        }
        else {
            ProfileErrorView()
        }

        // Logout Button
        LogoutButton(
            color = pokemon?.type?.color ?: Color.LightGray
        )
    }
}

@Composable
private fun ProfileView(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = pokemon.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(pokemon.type.color.copy(alpha = 0.1f))
        )

        Spacer(modifier = Modifier.height(50.dp))

        ProfileInfoView(
            title = stringResource(R.string.setting_id_title),
            info = "test"
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfileInfoView(
            title = stringResource(R.string.setting_profile_title),
            info = pokemon.name.toFirstCharUpperCase()
        )
    }
}

@Composable
fun ProfileErrorView() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(50.dp))

        ProfileInfoView(
            title = stringResource(R.string.setting_id_title),
            info = "test"
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfileInfoView(
            title = stringResource(R.string.setting_profile_title),
            info = stringResource(R.string.setting_profile_error_message)
        )
    }
}

@Composable
private fun ProfileInfoView(title: String, info: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            info,
            fontSize = 18.sp
        )
    }
}

@Composable
private fun LogoutButton(color: Color) {
    Text(
        "로그아웃",
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = Color.White,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(color.copy(alpha = 0.8f))
            .padding(horizontal = 60.dp, vertical = 10.dp)
            .clickable {
                // TODO
            }
    )
}

@Composable
private fun SettingLoadingView() {
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
        modifier = Modifier.padding(60.dp)
    ) {
        Spacer(Modifier.height(30.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray.copy(alpha = alpha)),
            )
        }

        Spacer(Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .height(30.dp)
                .width(350.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray.copy(alpha = alpha)),
        )

        Spacer(Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .height(30.dp)
                .width(350.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray.copy(alpha = alpha)),
        )
    }
}

@Preview
@Composable
fun SettingHaveDataPreview() {
    SettingContentScreen (
        onBackClick = {},
        pokemon = Pokemon(
            name = "ditto",
            type = PokemonType.Normal,
            thumbnail = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
            id = 132
        )
    )
}

@Preview
@Composable
fun SettingErrorPreview() {
    SettingContentScreen (
        onBackClick = {},
        pokemon = null
    )
}