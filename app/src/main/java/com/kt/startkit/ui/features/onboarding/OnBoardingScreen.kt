package com.kt.startkit.ui.features.onboarding

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.features.onboarding.common.StepState
import com.kt.startkit.ui.features.onboarding.common.Stepper
import com.kt.startkit.ui.features.onboarding.find_pokemon.FindPokemonViewModel
import com.kt.startkit.ui.features.onboarding.find_pokemon.step.FindPokemonStep
import com.kt.startkit.ui.navigator.AppNavigationRoute
import com.kt.startkit.ui.navigator.navigate
import timber.log.Timber

@Composable
fun OnBoardingScreen(
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
) {
    val navController = LocalNavigationProvider.current
    Stepper(
        steps = listOf(
            FindPokemonStep.Intro(
                state = StepState.Indexed,
                title = "안녕하세요! 사용자 님의 포켓몬을 찾아드릴게요",
                onStepPrevious = {
                    Timber.d("stepper Intro previous")
                },
                onStepNext = {
                    Timber.d("stepper Intro Next")
                },
            ),
            FindPokemonStep.UserProfile(
                state = StepState.Indexed,
                title = "이름과 나이를 알려주세요",
                onStepPrevious = {
                    Timber.d("${navController.backQueue.map { it.destination }} stepper userinput previous")
                },
                onStepNext = {
                    Timber.d("stepper userinput next")
                },
            ),
            FindPokemonStep.TravelSite(
                state = StepState.Indexed,
                title = "어디를 더 선호하나요?",
                onStepPrevious = {
                    Timber.d("stepper TravelSite previous")
                },
                onStepNext = {
                    Timber.d("stepper TravelSite Next")
                },
            ),
            FindPokemonStep.Routine(
                state = StepState.Indexed,
                title = "오늘 계획이 뭔가요? ",
                onStepPrevious = {
                    Timber.d("stepper Routine previous")
                },
                onStepNext = {
                    Timber.d("stepper Routine Next")
                },
            ),
            FindPokemonStep.Complete(
                state = StepState.Indexed,
                title = "끝났어요! 다음 페이지를 넘기면 포켓몬을 알려드릴게요",
                onStepPrevious = {
                    Timber.d("stepper Complete previous")
                },
                onStepNext = {
                    Timber.d("stepper Complete Next")
                    navController.navigate(AppNavigationRoute.ROOT) {
                        navController.popBackStack()
                        it.graph.setStartDestination(AppNavigationRoute.ROOT.routeName)
                    }
                },
            ),
        ),
    )
}
