package com.kt.startkit.ui.features.onboarding

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.kt.startkit.R
import com.kt.startkit.core.datastore.PreferenceDataStore
import com.kt.startkit.ui.features.main.LocalNavigationProvider
import com.kt.startkit.ui.features.onboarding.common.Step
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonState
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonView
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.FindPokemonStep
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.Screen
import com.kt.startkit.ui.navigator.AppNavigationRoute
import com.kt.startkit.ui.navigator.navigate

@Composable
fun OnBoardingStepper(
    steps: List<Step>,
    stepperViewModel: OnBoardingViewModel = hiltViewModel(),
) {

    val navController = LocalNavigationProvider.current
    val stepperState by stepperViewModel.viewState.collectAsStateWithLifecycle()
    val step = steps[stepperState.currentStep]
//    var progress: Float = 0.0f
//
//    LaunchedEffect(step) {
//        progress = (stepperState.currentStep / steps.size).toFloat()
//    }

    Column(Modifier.fillMaxHeight()) {
//        LinearProgressIndicator(
//            progress = progress,
//            modifier = Modifier.fillMaxWidth(),
//
//        )
        Text(steps[stepperState.currentStep].title, style = MaterialTheme.typography.h2)
        when (step) {
            is FindPokemonStep -> {
                FindPokemonView(
                    findPokemonStep = step,
                    onPrevious = {
                        stepperViewModel.updateStep(stepperState.currentStep - 1)
                    }, onNext = {
                        stepperViewModel.updateStep(stepperState.currentStep + 1)
                    })
            }
        }
    }
    if (stepperState is OnBoardingState.OnBoardingCancel) {
        OnBoardingDialog({ stepperViewModel.updateStep(stepperState.currentStep) }, navController)
    }
    if (stepperState is OnBoardingState.OnBoardingComplete) {
        navController.navigate(AppNavigationRoute.ROOT) {
            navController.popBackStack()
            it.graph.setStartDestination(AppNavigationRoute.ROOT.routeName)
        }
    }
}

@Composable
fun OnBoardingDialog(onDismiss: () -> Unit, navController: NavController) {
//    val openDialog = remember {
//        mutableStateOf(value)
//    }
    val activity = (LocalContext.current as? Activity)
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text("나가면 종료됩니다.")
            },
            text = {
                Text("다음에 다시 실행하실래요?")
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text("아니오")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        activity?.finish()
                    }
                ) {
                    Text("예")
                }
            },
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false,
            )
        )
}
