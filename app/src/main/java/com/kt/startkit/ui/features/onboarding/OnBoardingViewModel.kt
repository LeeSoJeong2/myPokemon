package com.kt.startkit.ui.features.onboarding

import androidx.lifecycle.viewModelScope
import com.kt.startkit.ui.features.onboarding.common.StepperViewModel
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.MajorStep
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : StepperViewModel<OnBoardingState>(
    OnBoardingState.OnBoardingInProgress(
        currentStep = 0,
        totalSteps = MajorStep.values().size
    )
) {
    override fun updateStep(nextIndex: Int) {
        viewModelScope.launch {
            if (nextIndex in 0 until viewState.value.totalSteps) {
                updateState {
                    OnBoardingState.OnBoardingInProgress(nextIndex, viewState.value.totalSteps)
                }
                return@launch
            }
            updateState {
                OnBoardingState.OnBoardingComplete(
                    viewState.value.currentStep,
                    viewState.value.totalSteps
                )
            }
        }
    }
}
