package com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step

import com.kt.startkit.ui.features.onboarding.common.Step

data class FindPokemonStep(
    override val title: String = "포켓몬 찾기",
    override val subSteps: List<FindPokemonSubStep> = FindPokemonSubStepType.values().map { it.convertToSubStep() }
): Step()

enum class MajorStep(
    val step: Step
) {
    FindPokemonStep(step = FindPokemonStep())
}
