package com.kt.startkit.ui.features.onboarding.find_pokemon.step

import androidx.compose.runtime.Composable
import com.kt.startkit.ui.features.onboarding.common.Step
import com.kt.startkit.ui.features.onboarding.common.StepState
import com.kt.startkit.ui.features.onboarding.common.SubStep
import com.kt.startkit.ui.features.onboarding.find_pokemon.FindPokemonViewModel
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.CompleteView
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.IntroView
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.RoutineView
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.TravelSiteView
import com.kt.startkit.ui.features.onboarding.find_pokemon.view.UserProfileView
import timber.log.Timber

/// step 0: introducing~
/// step 1: 이름, 나이
/// step 2: 도시, 자연
/// step 3: 집 가기, 야근하기
/// step 4: 완료(선택한 결과 reduce -> 결과 retrofit fetch
data class FindPokemonStep(
    override val title: String = "포켓몬 찾기",
    override val subSteps: List<FindPokemonSubStep> = listOf(
        FindPokemonSubStep.Intro(
            state = StepState.Indexed,
            title = "안녕하세요! 사용자 님의 포켓몬을 찾아드릴게요",
            onStepPrevious = {
                Timber.d("stepper Intro previous")
            },
            onStepNext = {
                Timber.d("stepper Intro Next")
            },
        ),
        FindPokemonSubStep.UserProfile(
            state = StepState.Indexed,
            title = "이름과 나이를 알려주세요",
            onStepPrevious = {
                //Timber.d("${navController.backQueue.map { it.destination }} stepper userinput previous")
            },
            onStepNext = {
                Timber.d("stepper userinput next")
            },
        ),
        FindPokemonSubStep.TravelSite(
            state = StepState.Indexed,
            title = "어디를 더 선호하나요?",
            onStepPrevious = {
                Timber.d("stepper TravelSite previous")
            },
            onStepNext = {
                Timber.d("stepper TravelSite Next")
            },
        ),
        FindPokemonSubStep.Routine(
            state = StepState.Indexed,
            title = "오늘 계획이 뭔가요? ",
            onStepPrevious = {
                Timber.d("stepper Routine previous")
            },
            onStepNext = {
                Timber.d("stepper Routine Next")
            },
        ),
        FindPokemonSubStep.Complete(
            state = StepState.Indexed,
            title = "끝났어요! 다음 페이지를 넘기면 포켓몬을 알려드릴게요",
            onStepPrevious = {
                Timber.d("stepper Complete previous")
            },
            onStepNext = {
                Timber.d("stepper Complete Next")
            },
        ),
    ),
): Step()
