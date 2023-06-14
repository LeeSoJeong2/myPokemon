package com.kt.startkit.ui.features.onboarding.common

import com.kt.startkit.core.base.ViewState

// step -> stepper -> on boarding

abstract class StepperState: ViewState() {
    abstract val currentStep: Int
    abstract val totalSteps: Int
}

enum class StepperType {
    Horizontal,
    Vertical
}
