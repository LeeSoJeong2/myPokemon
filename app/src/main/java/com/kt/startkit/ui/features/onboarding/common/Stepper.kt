package com.kt.startkit.ui.features.onboarding.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kt.startkit.R
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.features.onboarding.OnBoardingState
import com.kt.startkit.ui.features.onboarding.OnBoardingViewModel
import com.kt.startkit.ui.features.onboarding.find_pokemon.FindPokemonState
import com.kt.startkit.ui.features.onboarding.find_pokemon.FindPokemonViewModel
import com.kt.startkit.ui.features.onboarding.find_pokemon.step.FindPokemonStep
import com.kt.startkit.ui.features.onboarding.find_pokemon.step.FindPokemonSubStep
import com.kt.startkit.ui.features.onboarding.find_pokemon.step.Screen
import com.kt.startkit.ui.navigator.AppNavigationRoute
import com.kt.startkit.ui.navigator.navigate

/// LIst<Step>을 when으로 분기해서 해당 스탭의 상태가 complete이 되면 onboardingstep next호출
/// 해당 스탭의  뷰모델이 currentStep을 들고 있음. 현재스탭.subdteps[currentStep].screen 이용
@Composable
fun Stepper(
    steps: List<Step>,
    stepperViewModel: OnBoardingViewModel = hiltViewModel(),
    findPokemonViewModel: FindPokemonViewModel = hiltViewModel(),
    // TODO 모든 viewmodel을 fetch해올 수 잇으면 좋겟는데,,
) {

    val state by stepperViewModel.viewState.collectAsStateWithLifecycle()
    val navController = LocalNavigationProvider.current
    val findPokemonState by findPokemonViewModel.viewState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxHeight()) {
        Text(steps[state.currentStep].title, style = MaterialTheme.typography.h2)
        when (steps[state.currentStep]) {
            is FindPokemonStep -> {
                val findPokemonStep = steps[state.currentStep] as FindPokemonStep
                val subCurrentStep = findPokemonStep.subSteps[findPokemonState.currentStep]
                Column {
                    subCurrentStep.Screen(
                        findPokemonViewModel
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        if (state.currentStep > 0) Button(
                            onClick = {
                                findPokemonViewModel.updateStep(findPokemonState.currentStep - 1)
                            }
                        ) {
                            Text(stringResource(R.string.previous_step))
                        }
                        Button(
                            onClick = {
                                findPokemonViewModel.updateStep(findPokemonState.currentStep + 1)
                            }
                        ) {
                            Text(stringResource(R.string.next_step))
                        }
                    }
                    if (findPokemonState is FindPokemonState.FindPokemonComplete) {
                        stepperViewModel.updateStep(state.currentStep + 1)
                    }
                    if (state is OnBoardingState.OnBoardingComplete) {
                        navController.navigate(AppNavigationRoute.ROOT) {
                            navController.popBackStack()
                            it.graph.setStartDestination(AppNavigationRoute.ROOT.routeName)
                        }
                    }
                }
            }
        }
    }
}
