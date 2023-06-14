package com.kt.startkit.ui.features.onboarding.find_pokemon.step

import androidx.compose.runtime.Composable
import com.kt.startkit.ui.features.onboarding.common.StepState
import com.kt.startkit.ui.features.onboarding.common.SubStep
import com.kt.startkit.ui.features.onboarding.find_pokemon.FindPokemonViewModel
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.CompleteView
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.IntroView
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.RoutineView
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.TravelSiteView
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.UserProfileView

sealed class FindPokemonSubStep: SubStep() {
    data class Intro(
        override val state: StepState,
        override val title: String,
        override val onStepNext: () -> Unit,
        override val onStepPrevious: () -> Unit
    ): FindPokemonSubStep()
    data class UserProfile(
        override val state: StepState,
        override val title: String,
        override val onStepNext: () -> Unit,
        override val onStepPrevious: () -> Unit
    ): FindPokemonSubStep()

    class TravelSite(
        override val state: StepState,
        override val title: String,
        override val onStepNext: () -> Unit,
        override val onStepPrevious: () -> Unit
    ): FindPokemonSubStep()

    class Routine(
        override val state: StepState,
        override val title: String,
        override val onStepNext: () -> Unit,
        override val onStepPrevious: () -> Unit
    ): FindPokemonSubStep()

    class Complete(
        override val state: StepState,
        override val title: String,
        override val onStepNext: () -> Unit,
        override val onStepPrevious: () -> Unit
    ): FindPokemonSubStep()
}

@Composable
fun FindPokemonSubStep.Screen(findPokemonViewModel: FindPokemonViewModel) {
    when(this) {
        is FindPokemonSubStep.Intro -> {
            IntroView(findPokemonViewModel)
        }
        is FindPokemonSubStep.UserProfile -> {
            UserProfileView(findPokemonViewModel)
        }
        is FindPokemonSubStep.TravelSite -> {
            TravelSiteView(findPokemonViewModel)
        }
        is FindPokemonSubStep.Routine -> {
            RoutineView(findPokemonViewModel)
        }
        is FindPokemonSubStep.Complete -> {
            CompleteView(findPokemonViewModel)
        }
    }
}
