package com.kt.startkit.ui.features.onboarding.step.find_pokemon.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel

@Composable
fun UserProfileView(
    findPokemonViewModel: FindPokemonViewModel,
) {
    Text("이름과 나이를 알려주세요")
}
