package com.kt.startkit.ui.features.start

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.core.datastore.PreferenceDataStore
import com.kt.startkit.core.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
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
//                    userProfileRepository.fetchProfile()
//                    updateState {
//                        StartScreenState.NavigateToMain
//                    }
//                    return@launch
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
        delay(300)
        return true
//        return preferenceDataStore.isAutoLogin().first()
    }
}
