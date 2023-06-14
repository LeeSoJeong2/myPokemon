package com.kt.startkit.ui.features.main.home.detail

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : StateViewModel<PokemonDetailState>(initialState = PokemonDetailState.Initial) {
    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            updateState {
                PokemonDetailState.Fetching
            }

            try {
                val pokemon = pokemonRepository.getPokemonDetail(name)

                updateState {
                    PokemonDetailState.Fetched(pokemon = pokemon)
                }
            } catch (e: Exception) {
                updateState {
                    PokemonDetailState.Error("Fail To Fetch $name $e")
                }
            }
        }
    }
}