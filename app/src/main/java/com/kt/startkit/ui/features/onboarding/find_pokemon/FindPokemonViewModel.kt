package com.kt.startkit.ui.features.onboarding.find_pokemon

import androidx.lifecycle.viewModelScope
import com.kt.startkit.ui.features.onboarding.common.StepperState
import com.kt.startkit.ui.features.onboarding.common.StepperViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

// TODO 지금은 온보딩이 단계마다 값을 저장했다가 최종 단계에서 네트워크 콜을 함. 단계마다 콜 하는 경우에 각 단계마다 State가 꼭 필요해보임

@HiltViewModel
class FindPokemonViewModel @Inject constructor(
    private val useCase: FindPokemonUseCase
) : StepperViewModel<FindPokemonState>(FindPokemonState.FindPokemonInProgress(0, 5, 0)) {

    fun increase(addedScore: Int) {
        Timber.d("score: ${viewState.value.score}")
        viewModelScope.launch {
            updateState {
                FindPokemonState.FindPokemonInProgress(viewState.value.currentStep, viewState.value.totalSteps, this.score + addedScore)
            }
        }
    }

    override fun updateStep(nextIndex: Int) {
        viewModelScope.launch {
            if(nextIndex in 0 until viewState.value.totalSteps) {
                updateState {
                    FindPokemonState.FindPokemonInProgress(nextIndex, viewState.value.totalSteps, viewState.value.score)
                }
                return@launch
            }
            updateState {
                FindPokemonState.FindPokemonComplete(viewState.value.currentStep, viewState.value.totalSteps, viewState.value.score)
            }
        }
    }
}
