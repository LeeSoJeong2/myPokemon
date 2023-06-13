package com.kt.startkit.ui.features.main.home

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun observePokemonInfo() {
        viewModelScope.launch {
            pokemonRepository.pokemonInfo
                .onEach {
                    if (it == null) {
                        updateState { HomeState.Error("Fail to load pokemon!!") }
                    } else {
                        updateState { HomeState.Data(it) }
                    }
                }
                .collect()
        }
    }


    fun observePokemonMap(){
        viewModelScope.launch {
            pokemonRepository.pokemon
                .onEach {
                    if (viewState.value is HomeState.Updated) {
                        val updated = (viewState.value as HomeState.Updated).pokemonList.toMutableList()
                        if (it != null) {
                            updated.add(it)
                            updateState { HomeState.Updated(updated) }
                        }
                    }
                    else {
                        if (it != null) {
                            val updated = listOf(it)
                            updateState { HomeState.Updated(updated)}
                        }
                    }
                }
                .collect()
        }
    }

    fun fetchPokemon(names: List<String>) {
        viewModelScope.launch {
            names.forEach {
                pokemonRepository.fetchPokemon(it)
            }
        }
    }



//    when (val previousState = viewState.value) {
//        is HomeState.Data -> {
//            try {
//                val pokemon = pokemonRepository.fetchPokemon(name)
//                updateState {
//                    HomeState.Data(previousState.pokemonInfo, pokemon = pokemon)
//                }
//            } catch (e: Exception) {
//                Logger.d("fetch $name pokemon fail $e")
//            }
//        }
//        else -> {}
//    }
//    fun fetchData(page: Int) {
//        viewModelScope.launch {
//            updateState { HomeState.Loading }
//            delay(1000)
//
//            try {
//                pokemonRepository.fetchPokemonInfo(page)
//            } catch (e: Exception) {
//                updateState { HomeState.Error("Unknown error") }
//            }
//        }
//    }
}