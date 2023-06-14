package com.kt.startkit.ui.features.main.setting

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.pokemon.Pokemon

// State Hierarchy
//
// SettingState
//    |- Initial: 초기 상태
//    |- Fetching : Preference 에서 저장된 name 을 읽어 오고, 해당 name 의 Pokemon 정보를 가져오고 있는 상태.
//    |- Fetched : Pokemon 정보를 패치 완료한 상태.
//    |- Error : 서버 패치 or Preference 정보 패치에 실패한 상태.
sealed class SettingState : ViewState(){
    object Initial : SettingState()

    object Fetching: SettingState()

    data class Fetched(val pokemon: Pokemon) : SettingState()

    data class Error(val message: String) : SettingState()
}