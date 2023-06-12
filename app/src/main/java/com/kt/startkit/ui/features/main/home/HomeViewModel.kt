package com.kt.startkit.ui.features.main.home

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.repository.PokemonRepository
import com.kt.startkit.domain.usecase.ItemUsecase
import com.kt.startkit.ui.features.main.root.RootViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : StateViewModel<HomeState>(initialState = HomeState.Initial) {

//    override fun setInitialState(): HomeViewState {
//        return HomeViewState.Initial
//    }

    fun observeUserProfile() {
        viewModelScope.launch {
            pokemonRepository.pokemonInfo
                .onEach {
                    if (it == null) {
                        updateState { HomeState.Error("Fail to load pokemon!!") }
                    } else {
                        updateState { HomeState.Data(pokemonInfo = it) }
                    }
                }
                .collect()
        }
    }

    fun fetchData(page: Int) {
        viewModelScope.launch {
            updateState { HomeState.Loading }
            delay(1000)

            try {
                pokemonRepository.fetchPokemon(page)
            } catch (e: Exception) {
                updateState { HomeState.Error("Unknown error") }
            }
        }
    }
}