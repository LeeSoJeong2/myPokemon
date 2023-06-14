package com.kt.startkit.ui.features.main.setting

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.core.datastore.PreferenceDataStore
import com.kt.startkit.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val preferenceDataStore: PreferenceDataStore,
) : StateViewModel<SettingState>(initialState = SettingState.Initial) {
    fun fetchProfile() {
        viewModelScope.launch {
            updateState {
                SettingState.Loading
            }

            try {
                // TODO: DataStore 에서 값 읽기
                // TO TEST
                val name = "ditto"
                val pokemon = pokemonRepository.getPokemon(name)
                updateState {
                    SettingState.Data(pokemon = pokemon)
                }

            } catch (e: Exception) {
                updateState {
                    SettingState.Error("Fail to load profile $e")
                }
            }
        }
    }
}