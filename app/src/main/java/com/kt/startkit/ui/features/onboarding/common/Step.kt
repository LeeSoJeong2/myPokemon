package com.kt.startkit.ui.features.onboarding.common

abstract class Step {
   abstract val title: String
   abstract val subSteps: List<SubStep>
}
