package com.kt.startkit.ui.features.onboarding

import androidx.compose.runtime.Composable
import com.kt.startkit.ui.features.onboarding.step.MajorStep
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.FindPokemonStep

@Composable
fun OnBoardingScreen() {
    OnBoardingStepper(
        steps = MajorStep.values().map { it.step }
    )
}
