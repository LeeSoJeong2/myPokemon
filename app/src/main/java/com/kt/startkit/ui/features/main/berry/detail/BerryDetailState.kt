package com.kt.startkit.ui.features.main.berry.detail

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.berry.BerryDetail

sealed class BerryDetailState: ViewState() {
    object Initial: BerryDetailState()
    object Loading: BerryDetailState()
    data class Data(val berryDetail: BerryDetail): BerryDetailState()
    data class Error(val message: String): BerryDetailState()
}