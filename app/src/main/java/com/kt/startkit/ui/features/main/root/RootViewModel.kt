package com.kt.startkit.ui.features.main.root

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : StateViewModel<RootViewState>(initialState = RootViewState.Initial) {

    fun observeUserProfile() {
        viewModelScope.launch {
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            pokemonRepository.pokemonInfo
                .onEach {
                    if (it == null) {
                        updateState { RootViewState.Error("Fail to load pokemon!!") }
                    } else {
                        updateState { RootViewState.Fetched(pokemonInfo = it) }
                    }
                }
                .collect()
//            }
        }
    }

}