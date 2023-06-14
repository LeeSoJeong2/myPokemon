package com.kt.startkit.ui.features.onboarding.common

abstract class SubStep {
    abstract val state: StepState
    abstract val title: String
    abstract val onStepNext: () -> Unit
    abstract val onStepPrevious: () -> Unit
}
