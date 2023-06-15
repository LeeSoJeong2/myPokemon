package com.kt.startkit.ui.features.onboarding.step

import com.kt.startkit.ui.features.onboarding.common.Step
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.FindPokemonStep

enum class MajorStep(
    val step: Step
) {
    FindPokemonStep(step = FindPokemonStep())
}
