package com.kt.startkit.ui.features.onboarding

import com.kt.startkit.core.base.StateViewModel
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    //val usecase: FindPokemonUseCase
): StateViewModel<OnBoardingState>(OnBoardingState.Init) {
    /// 최초, 진행 중, 끝의 상태를 발행 및 옵저빙

}
