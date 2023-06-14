package com.kt.startkit.ui.features.main.berry

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.usecase.BerryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerryScreenViewModel @Inject constructor(
    private val berryUseCase: BerryUseCase
) : StateViewModel<BerryViewState>(initialState = BerryViewState.Initial) {

    fun fetchInitialData() {
        viewModelScope.launch {
            updateState { BerryViewState.Loading }
            try {
                val berriesResponse = berryUseCase.getBerriesResponse()
                updateState { BerryViewState.Data(berriesResponse) }
            } catch (e: Exception) {
                updateState { BerryViewState.Error("Unknown error") }
            }
        }
    }
}