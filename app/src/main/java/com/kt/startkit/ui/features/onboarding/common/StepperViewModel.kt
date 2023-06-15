package com.kt.startkit.ui.features.onboarding.common

import com.kt.startkit.core.base.StateViewModel

// StepperViewModel
//      ㄴ OnBoardingViewModel
//      ㄴ FindPokemonViewModel

// step 에 사용하는 ViewModel
// step 이동을 위해 updateStep() 필수로 구현 하기
/// TODO interface
abstract class StepperViewModel<StepperUiState : StepperState> constructor(initialState: StepperUiState) :
    StateViewModel<StepperUiState>(initialState) {
    abstract fun updateStep(nextIndex: Int)
}
