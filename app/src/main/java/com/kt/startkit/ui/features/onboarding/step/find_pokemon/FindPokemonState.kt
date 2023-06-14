package com.kt.startkit.ui.features.onboarding.step.find_pokemon

import com.kt.startkit.ui.features.onboarding.common.StepperState


sealed class FindPokemonState(
    override val currentStep: Int,
    override val totalSteps: Int,
    val score: Int
) : StepperState() {

    class FindPokemonInProgress(
        override val currentStep: Int,
        override val totalSteps: Int,
        score: Int
    ) :
        FindPokemonState(currentStep, totalSteps, score)

    class FindPokemonComplete(
        override val currentStep: Int,
        override val totalSteps: Int,
        score: Int
    ) :
        FindPokemonState(currentStep, totalSteps, score)
}
