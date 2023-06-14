package com.kt.startkit.ui.features.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kt.startkit.R
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.features.onboarding.common.Step
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonState
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.FindPokemonStep
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.Screen
import com.kt.startkit.ui.navigator.AppNavigationRoute
import com.kt.startkit.ui.navigator.navigate

@Composable
fun OnBoardingStepper(
    steps: List<Step>,
    stepperViewModel: OnBoardingViewModel = hiltViewModel(),
    findPokemonViewModel: FindPokemonViewModel = hiltViewModel(),
    // TODO 모든 viewmodel을 fetch해올 수 있으면 좋겠다,,
) {

    val navController = LocalNavigationProvider.current
    val stepperState by stepperViewModel.viewState.collectAsStateWithLifecycle()
    val findPokemonState by findPokemonViewModel.viewState.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxHeight()) {
        Text(steps[stepperState.currentStep].title, style = MaterialTheme.typography.h2)
        when (steps[stepperState.currentStep]) {
            is FindPokemonStep -> {
                val findPokemonStep = steps[stepperState.currentStep] as FindPokemonStep
                val subCurrentStep = findPokemonStep.subSteps[findPokemonState.currentStep]
                Column {
                    subCurrentStep.Screen(
                        findPokemonViewModel
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        if (stepperState.currentStep > 0) Button(
                            onClick = {
                                subCurrentStep.onStepPrevious()
                                findPokemonViewModel.updateStep(findPokemonState.currentStep - 1)
                            }
                        ) {
                            Text(stringResource(R.string.previous_step))
                        }
                        Button(
                            onClick = {
                                subCurrentStep.onStepNext()
                                findPokemonViewModel.updateStep(findPokemonState.currentStep + 1)
                            }
                        ) {
                            Text(stringResource(R.string.next_step))
                        }
                    }
                    if (findPokemonState is FindPokemonState.FindPokemonComplete) {
                        stepperViewModel.updateStep(stepperState.currentStep + 1)
                    }
                    if (stepperState is OnBoardingState.OnBoardingComplete) {
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
