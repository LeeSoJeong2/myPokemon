package com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step

import androidx.compose.runtime.Composable
import com.kt.startkit.ui.features.onboarding.common.StepState
import com.kt.startkit.ui.features.onboarding.common.SubStep
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.view.CompleteView
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.view.IntroView
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.view.RoutineView
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.view.TravelSiteView
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.view.UserProfileView
import timber.log.Timber

enum class FindPokemonSubStepType {
    Intro,
    UserProfile,
    TravelSite,
    Routine,
    Complete;

    fun convertToSubStep(): FindPokemonSubStep {
        when(this) {
            Intro -> {
                return FindPokemonSubStep.Intro(
                    state = StepState.Indexed,
                    title = "안녕하세요! 사용자 님의 포켓몬을 찾아드릴게요",
                    onStepPrevious = {
                        Timber.d("stepper Intro previous")
                    },
                    onStepNext = {
                        Timber.d("stepper Intro Next")
                    },
                )
            }
            UserProfile -> {
                return FindPokemonSubStep.UserProfile(
                    state = StepState.Indexed,
                    title = "트레이너 정보",
                    onStepPrevious = {
                        //Timber.d("${navController.backQueue.map { it.destination }} stepper userinput previous")
                    },
                    onStepNext = {
                        Timber.d("stepper userinput next")
                    },
                )
            }
            TravelSite -> {
                return FindPokemonSubStep.TravelSite(
                    state = StepState.Indexed,
                    title = "선호 장소",
                    onStepPrevious = {
                        Timber.d("stepper TravelSite previous")
                    },
                    onStepNext = {
                        Timber.d("stepper TravelSite Next")
                    },
                )
            }
            Routine -> {
                return FindPokemonSubStep.Routine(
                    state = StepState.Indexed,
                    title = "오늘 계획 ",
                    onStepPrevious = {
                        Timber.d("stepper Routine previous")
                    },
                    onStepNext = {
                        Timber.d("stepper Routine Next")
                    },
                )
            }
            Complete -> {
                return FindPokemonSubStep.Complete(
                    state = StepState.Indexed,
                    title = "완료",
                    onStepPrevious = {
                        Timber.d("stepper Complete previous")
                    },
                    onStepNext = {
                        Timber.d("stepper Complete Next")
                    },
                )
            }
        }
    }
}

/// FindPokemonStep 하위 step :
/// step 0: introducing~
/// step 1: 이름, 나이x
/// step 2: 도시, 자연
/// step 3: 집 가기, 야근하기
/// step 4: 완료(선택한 결과 reduce -> 결과 retrofit fetch

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
