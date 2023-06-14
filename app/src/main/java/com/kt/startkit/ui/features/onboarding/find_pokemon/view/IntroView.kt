package com.kt.startkit.ui.features.onboarding.find_pokemon.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kt.startkit.ui.features.onboarding.find_pokemon.FindPokemonViewModel

@Composable
fun IntroView(
    findPokemonViewModel: FindPokemonViewModel,
) {
    findPokemonViewModel.increase(1)
}
