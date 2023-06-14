package com.kt.startkit.ui.features.onboarding.step.find_pokemon

import androidx.lifecycle.viewModelScope
import com.kt.startkit.domain.usecase.FindPokemonUseCase
import com.kt.startkit.ui.features.onboarding.common.StepperViewModel
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.sub_step.FindPokemonSubStepType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FindPokemonViewModel @Inject constructor(
    private val useCase: FindPokemonUseCase
) : StepperViewModel<FindPokemonState>(
    FindPokemonState.FindPokemonInProgress(
        0,
        FindPokemonSubStepType.values().size,
        0
    )
) {

    fun increase(addedScore: Int) {
        Timber.d("score: ${viewState.value.score}")
        viewModelScope.launch {
            updateState {
                FindPokemonState.FindPokemonInProgress(
                    viewState.value.currentStep,
                    viewState.value.totalSteps,
                    this.score + addedScore
                )
            }
        }
    }

    override fun updateStep(nextIndex: Int) {
        viewModelScope.launch {
            if (nextIndex in 0 until viewState.value.totalSteps) {
                updateState {
                    FindPokemonState.FindPokemonInProgress(
                        nextIndex,
                        viewState.value.totalSteps,
                        viewState.value.score
                    )
                }
                return@launch
            }
            updateState {
                FindPokemonState.FindPokemonComplete(
                    viewState.value.currentStep,
                    viewState.value.totalSteps,
                    viewState.value.score
                )
            }
        }
    }
}
