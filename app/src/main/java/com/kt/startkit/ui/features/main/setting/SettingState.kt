package com.kt.startkit.ui.features.main.setting

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.pokemon.Pokemon

sealed class SettingState : ViewState(){
    object Initial : SettingState()

    object Loading: SettingState()

    data class Data(val pokemon: Pokemon) : SettingState()

    data class Error(val message: String) : SettingState()
}