package com.kt.startkit.ui.features.onboarding.step.find_pokemon.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel

@Composable
fun CompleteView(
    findPokemonViewModel: FindPokemonViewModel,
) {
    val state by findPokemonViewModel.viewState.collectAsStateWithLifecycle()
    Text("끝났어요! 다음 페이지로 넘기면 회원 님의 포켓몬을 알려드릴게요 ${state.score}")
}
