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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kt.startkit.ui.features.onboarding.common.Step
import com.kt.startkit.ui.features.onboarding.find_pokemon.step.screen

@Composable
fun Stepper(
    steps: List<Step>,
    stepperViewModel: StepperViewModel = hiltViewModel(),
) {

    val state by stepperViewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        stepperViewModel.setTotalStep(steps.size)
    }

    Column(Modifier.fillMaxHeight()) {
        Text(steps[state.currentStep].title, style = MaterialTheme.typography.h2)
        steps[state.currentStep].screen()
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            if(state.currentStep > 0) Button(onClick = {
                steps[state.currentStep].onStepPrevious()
                stepperViewModel.updateStep(state.currentStep-1)
            }) {
                Text("이전으로")
            }
            Button(onClick = {
                steps[state.currentStep].onStepNext()
                stepperViewModel.updateStep(state.currentStep+1)
            }) {
                Text("다음으로")
            }
        }
    }

}
