package com.kt.startkit.ui.features.start

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.core.datastore.PreferenceDataStore
import com.kt.startkit.core.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val preferenceDataStore: PreferenceDataStore,
) : StateViewModel<StartState>(initialState = StartState.Loading) {

    fun fetchInitialData() {
        Logger.d("fetch initial data")
        viewModelScope.launch {
            try {
                if (shouldAppUpdate()) {
                    updateState { StartState.ShouldAppUpdate }
                    return@launch
                }

                // 자동 로그인 여부
                if (canAutoLogin()) {
                    updateState { StartState.NavigateToRouteScreen }
                }
                else {
                    updateState { StartState.NeedToLogin }
                }

            } catch (e: Exception) {
                Logger.e("fetching error :${e.message}")
                e.printStackTrace()
                updateState { StartState.FailToInitialize }
            }
        }
    }

    private suspend fun shouldAppUpdate(): Boolean {
        return false
    }

    private suspend fun canAutoLogin(): Boolean {
        return preferenceDataStore.isAutoLogin().first()
    }
}
