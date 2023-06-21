package com.kt.startkit.ui.features.main.berry

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.usecase.BerryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerryScreenViewModel @Inject constructor(
    private val berryUseCase: BerryUseCase,
) : StateViewModel<BerryState>(initialState = BerryState.Initial) {

    fun fetchInitialData() {
        viewModelScope.launch {
            updateState { BerryState.Loading }
            try {
                val berryInfo = berryUseCase.fetchBerryInfo()
                updateState { BerryState.Data(berryInfo) }
            } catch (e: Exception) {
                updateState { BerryState.Error("Unknown error") }
            }
        }
    }

}