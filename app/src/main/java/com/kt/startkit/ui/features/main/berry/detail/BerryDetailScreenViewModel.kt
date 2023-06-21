package com.kt.startkit.ui.features.main.berry.detail

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.usecase.BerryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerryDetailScreenViewModel @Inject constructor(
    private val berryUseCase: BerryUseCase ,
) : StateViewModel<BerryDetailState>(initialState = BerryDetailState.Initial) {

    fun fetchInitialData(berryName: String) {
        viewModelScope.launch {
            updateState { BerryDetailState.Loading }
            try {
                val berryDetail = berryUseCase.fetchBerryDetail(berryName)
                updateState { BerryDetailState.Data(berryDetail) }
            } catch (e: Exception) {
                updateState { BerryDetailState.Error("Unknown error") }
            }
        }
    }

}