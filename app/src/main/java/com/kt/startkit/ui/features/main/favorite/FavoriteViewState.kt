package com.kt.startkit.ui.features.main.favorite

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.UserProfile
import com.kt.startkit.ui.features.main.berry.BerryViewState

sealed class FavoriteViewState : ViewState() {
    object Initial : FavoriteViewState()
    object Loading: FavoriteViewState()
    data class Data(val userProfile: UserProfile): FavoriteViewState()
    data class Error(val message: String) : FavoriteViewState()
}