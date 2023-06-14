package com.kt.startkit.ui.features.onboarding.step.find_pokemon.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel

@Composable
fun IntroView(
    findPokemonViewModel: FindPokemonViewModel,
) {
    Text("안녕하세요! 환영합니다!!!!!")
}
