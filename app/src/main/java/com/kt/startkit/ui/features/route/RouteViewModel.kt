package com.kt.startkit.ui.features.route

import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.core.datastore.PreferenceDataStore
import com.kt.startkit.core.logger.Logger
import com.kt.startkit.domain.repository.PokemonRepository
import com.kt.startkit.ui.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val preferenceDataStore: PreferenceDataStore,
) : StateViewModel<RouteState>(initialState = RouteState.Loading) {

    fun fetchInitialData() {
        Logger.d("fetch initial data")
        viewModelScope.launch {
            try {
                pokemonRepository.fetchPokemonInfo(offset = Constants.PAGE_OFFSET)
                if (showOnBoarding()) {
                    updateState { RouteState.NavigateToOnBoarding }
                } else {
                    updateState { RouteState.NavigateToMain }
                }
            } catch (e: Exception) {
                Logger.e("fetching error :${e.message}")
                e.printStackTrace()
                updateState { RouteState.FailToInitialize }
            }
        }
    }
    private suspend fun showOnBoarding(): Boolean {
        return preferenceDataStore.needToShowOnBoarding().first()
    }
}
