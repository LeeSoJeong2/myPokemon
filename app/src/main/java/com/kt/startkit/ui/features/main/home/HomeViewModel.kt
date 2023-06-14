package com.kt.startkit.ui.features.main.home

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.core.logger.Logger
import com.kt.startkit.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : StateViewModel<HomeState>(initialState = HomeState.Initial(0)) {

    fun observePokemonInfo() {
        viewModelScope.launch {
            pokemonRepository.pokemonInfo
                .onEach {
                    if (it == null) {
                        updateState { HomeState.Error(viewState.value.currentPage, "Fail to load pokemon!!") }
                    } else {
                        updateState { HomeState.PokemonInfoFetched(viewState.value.currentPage, it) }
                    }
                }
                .collect()
        }
    }

    fun observePokemonList(){
        viewModelScope.launch {
            pokemonRepository.pokemon
                .onEach {
                    if (it == null) {
                        return@onEach
                    }
                    // 이전 state 가 PokemonListFetched 상태 라면, 이전 list 에 새로운 pokemon 을 추가 하여 발행.
                    if (viewState.value is HomeState.PokemonListFetched) {
                        val pokemonListFetched = (viewState.value as HomeState.PokemonListFetched).pokemonList.toMutableList()

                        pokemonListFetched.add(it)

                        updateState {
                            HomeState.PokemonListFetched(
                                viewState.value.currentPage,
                                (viewState.value as HomeState.PokemonListFetched).totalCount,
                                pokemonListFetched
                            )
                        }
                    }
                    // 이전 state 가 PokemonInfoFetched 상태 라면, 새로운 pokemon list 발행.
                    else if (viewState.value is HomeState.PokemonInfoFetched){
                        updateState {
                            HomeState.PokemonListFetched(
                                viewState.value.currentPage,
                                (viewState.value as HomeState.PokemonInfoFetched).pokemonInfo.count,
                                listOf(it)
                            )
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
                HomeState.Fetching(page)
            }

            try {
                pokemonRepository.fetchPokemonInfo(page = page)
            } catch(e: Exception) {
                updateState {
                    HomeState.Error(
                        viewState.value.currentPage,
                        "Fail to fetch pokemon info!!"
                    )
                }
            }
        }
    }
}