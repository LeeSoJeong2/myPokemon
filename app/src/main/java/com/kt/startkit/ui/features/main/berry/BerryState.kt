package com.kt.startkit.ui.features.main.berry

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.berry.BerryInfo

sealed class BerryState: ViewState() {
    object Initial: BerryState()
    object Loading: BerryState()
    data class Data(val berryInfo: BerryInfo): BerryState()
    data class Error(val message: String): BerryState()
}