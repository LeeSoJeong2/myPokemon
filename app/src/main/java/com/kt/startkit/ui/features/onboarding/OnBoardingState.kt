package com.kt.startkit.ui.features.onboarding

import com.kt.startkit.ui.features.onboarding.common.StepperState


sealed class OnBoardingState(override val currentStep: Int, override val totalSteps: Int) :
    StepperState() {

    data class OnBoardingCancel(override val currentStep: Int, override val totalSteps: Int) :
        OnBoardingState(currentStep, totalSteps)

    data class OnBoardingInProgress(override val currentStep: Int, override val totalSteps: Int) :
        OnBoardingState(currentStep, totalSteps)

    data class OnBoardingComplete(override val currentStep: Int, override val totalSteps: Int) :
        OnBoardingState(currentStep, totalSteps)
}
