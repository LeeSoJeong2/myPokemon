package com.kt.startkit.ui.features.main.home

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.core.logger.Logger
import com.kt.startkit.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : StateViewModel<HomeState>(initialState = HomeState.Initial(0)) {

//    override fun setInitialState(): HomeViewState {
//        return HomeViewState.Initial
//    }

    fun observePokemonInfo() {
        viewModelScope.launch {
            pokemonRepository.pokemonInfo
                .onEach {
                    if (it == null) {
                        updateState { HomeState.Error(viewState.value.currentPage, "Fail to load pokemon!!") }
                    } else {
                        updateState { HomeState.Data(viewState.value.currentPage, it) }
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
                            updateState { HomeState.Updated(
                                viewState.value.currentPage,
                                (viewState.value as HomeState.Updated).totalCount,
                                updated
                            ) }
                        }
                    }
                    else if (viewState.value is HomeState.Data){
                        if (it != null) {
                            val updated = listOf(it)
                            updateState { HomeState.Updated(
                                viewState.value.currentPage,
                                (viewState.value as HomeState.Data).pokemonInfo.count,
                                updated
                            )}
                        }
                    }
                }
                .collect()
        }
    }

    fun fetchPokemon(names: List<String>) {
        viewModelScope.launch {
            names.forEach {
                try {
                    pokemonRepository.fetchPokemon(it)
                } catch (e: Exception) {
                    Logger.d("fetch pokemon fail $it: $e")
                }

            }
        }
    }

    fun fetchPokemonInfo(page: Int) {
        viewModelScope.launch {
            updateState {
                HomeState.Updating(page)
            }
            pokemonRepository.fetchPokemonInfo(page = page)
        }
    }

}