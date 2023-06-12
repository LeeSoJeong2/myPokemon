package com.kt.startkit.ui.features.route

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.core.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(

) : StateViewModel<RouteState>(initialState = RouteState.Loading) {

    fun fetchInitialData() {
        Logger.d("fetch initial data")
        viewModelScope.launch {
            try {
                updateState { RouteState.NavigateToMain }
            } catch (e: Exception) {
                Logger.e("fetching error :${e.message}")
                e.printStackTrace()
                updateState { RouteState.FailToInitialize }
            }
        }
    }
}