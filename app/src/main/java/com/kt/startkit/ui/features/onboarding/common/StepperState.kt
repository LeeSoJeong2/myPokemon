package com.kt.startkit.ui.features.onboarding.common

import com.kt.startkit.core.base.ViewState

// StepperViewModel의 State
abstract class StepperState: ViewState() {
    abstract val currentStep: Int // 현재 step
    abstract val totalSteps: Int // step에서 갖고 있는 step 개수
    val stepperType: StepperType = StepperType.Horizontal // step 출력 방향?
}

enum class StepperType {
    Horizontal,
    Vertical
}
