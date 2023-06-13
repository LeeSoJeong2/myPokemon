package com.kt.startkit.ui.features.main.berry

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.Berry

sealed class BerryViewState: ViewState() {
    object Initial: BerryViewState()
    object Loading: BerryViewState()
    data class Data(val berries: List<Berry>): BerryViewState()
    data class Error(val message: String): BerryViewState()
}