package com.kt.startkit.ui.features.onboarding.find_pokemon

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.core.base.ViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class FindPokemonViewModel @Inject constructor(
    private val useCase: FindPokemonUseCase
): StateViewModel<FindPokemonState>(FindPokemonState(0)) {
    fun increase(addedScore: Int) {
        viewModelScope.launch {
            updateState {
                FindPokemonState(this.score + addedScore)
            }
        }
    }
}

data class FindPokemonState(val score: Int): ViewState()
