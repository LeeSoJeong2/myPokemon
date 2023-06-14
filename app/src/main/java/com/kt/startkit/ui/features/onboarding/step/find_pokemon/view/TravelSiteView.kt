package com.kt.startkit.ui.features.onboarding.step.find_pokemon.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel

@Composable
fun TravelSiteView(
    findPokemonViewModel: FindPokemonViewModel,
) {
    Text("선호 장소를 골라주세요")
}
