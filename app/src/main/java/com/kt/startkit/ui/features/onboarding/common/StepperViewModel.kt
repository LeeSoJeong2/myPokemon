package com.kt.startkit.ui.features.onboarding.common

import com.kt.startkit.core.base.StateViewModel

// onboardingvoewmodel (stepper - step update)
// findpokemon, routine viewmodel etc (stepper - step update)
abstract class StepperViewModel<StepperUiState : StepperState> constructor(initialState: StepperUiState) :
    StateViewModel<StepperUiState>(initialState) {
    abstract fun updateStep(nextIndex: Int)
}
