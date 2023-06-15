package com.kt.startkit.ui.features.main.favorite

import androidx.lifecycle.viewModelScope
import com.kt.startkit.core.base.StateViewModel
import com.kt.startkit.domain.repository.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
) : StateViewModel<FavoriteViewState>(initialState = FavoriteViewState.Initial) {

    fun observeUserProfile() {
        viewModelScope.launch {

            userProfileRepository.fetchProfile()

//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            userProfileRepository.profile
                .onEach {
                    if (it == null) {
                        updateState { FavoriteViewState.Error("Fail to load userProfile!!") }
                    } else {
                        updateState { FavoriteViewState.Data(userProfile = it) }
                    }
                }
                .collect()
//            }
        }
    }
}