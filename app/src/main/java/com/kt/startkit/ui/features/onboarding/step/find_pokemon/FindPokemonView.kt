package com.kt.startkit.ui.features.onboarding.step.find_pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kt.startkit.R
import com.kt.startkit.ui.features.onboarding.OnBoardingState
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.FindPokemonStep
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.Screen
import com.kt.startkit.ui.navigator.AppNavigationRoute
import com.kt.startkit.ui.navigator.navigate

@Composable
fun FindPokemonView(
    findPokemonStep: FindPokemonStep,
    findPokemonViewModel: FindPokemonViewModel = hiltViewModel(),
    onPrevious: () -> Unit,
    onNext: () -> Unit,
) {
    val findPokemonState by findPokemonViewModel.viewState.collectAsStateWithLifecycle()
    val subCurrentStep = findPokemonStep.subSteps[findPokemonState.currentStep]
    Column {
        subCurrentStep.Screen(
            findPokemonViewModel
        )
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Button(
                shape = RoundedCornerShape(20),
                onClick = {
                    if (findPokemonState.currentStep == 0)
                        onPrevious()
                    else
                        if (subCurrentStep.previousButtonText == null)
                            findPokemonViewModel.updateStep(findPokemonState.currentStep - 1)
                        else
                            subCurrentStep.onStepPrevious()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .background(Color.White),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)

            ) {
                Text(subCurrentStep.previousButtonText ?: "이전으로")
            }
            Button(
                shape = RoundedCornerShape(20),
                onClick = {
                    if (findPokemonState.currentStep == findPokemonStep.subSteps.size - 1)
                        onNext()
                    else

                    if (subCurrentStep.nextButtonText == null)
                        findPokemonViewModel.updateStep(findPokemonState.currentStep + 1)
                    else
                        subCurrentStep.onStepNext()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .background(Color.White),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)

            ) {
                Text(subCurrentStep.nextButtonText ?: "다음으로")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

//@Composable
//fun StepButton() {
//
//}
