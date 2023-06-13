package com.kt.startkit.ui.features.onboarding.common

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepperViewModel @Inject constructor(): StateViewModel<StepperEssentials>(StepperEssentials()) {

    private var totalStep: Int = 0

    fun updateStep(nextIndex: Int) {
        viewModelScope.launch {
            if(nextIndex in 0 until totalStep) {
                updateState {
                    StepperEssentials(currentStep = nextIndex)
                }
                return@launch
            }
        }
    }

    fun setTotalStep(size: Int) {
        totalStep = size
    }
}
