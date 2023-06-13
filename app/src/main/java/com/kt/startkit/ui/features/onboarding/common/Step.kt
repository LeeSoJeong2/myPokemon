package com.kt.startkit.ui.features.onboarding.common

abstract class Step {
    abstract val state: StepState
    abstract val title: String
    abstract val onStepNext: () -> Unit
    abstract val onStepPrevious: () -> Unit
}

enum class SubStepType {
    Horizontal,
    Vertical
}
