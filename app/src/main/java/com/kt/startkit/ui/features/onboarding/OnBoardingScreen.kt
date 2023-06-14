package com.kt.startkit.ui.features.onboarding

import androidx.compose.runtime.Composable
import com.kt.startkit.ui.features.onboarding.common.Stepper
import com.kt.startkit.ui.features.onboarding.find_pokemon.step.FindPokemonStep

@Composable
fun OnBoardingScreen() {
    Stepper(
        steps = listOf(FindPokemonStep()),
    )
}
