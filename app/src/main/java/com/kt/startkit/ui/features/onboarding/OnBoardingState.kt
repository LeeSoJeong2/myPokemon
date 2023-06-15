package com.kt.startkit.ui.features.onboarding

import com.kt.startkit.ui.features.onboarding.common.StepperState


sealed class OnBoardingState(override val currentStep: Int, override val totalSteps: Int) :
    StepperState() {

    class OnBoardingCancel(override val currentStep: Int, override val totalSteps: Int) :
        OnBoardingState(currentStep, totalSteps)
    class OnBoardingInProgress(override val currentStep: Int, override val totalSteps: Int) :
        OnBoardingState(currentStep, totalSteps)

    class OnBoardingComplete(override val currentStep: Int, override val totalSteps: Int) :
        OnBoardingState(currentStep, totalSteps)
}
