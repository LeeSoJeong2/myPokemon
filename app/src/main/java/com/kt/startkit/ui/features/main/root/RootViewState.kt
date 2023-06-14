package com.kt.startkit.ui.features.main.root

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.pokemon.PokemonInfo

// State Hierarchy
//
// RootState
//    |- Initial: 초기 상태
//    |- Fetched : 서버 데이터 패치 완료 후, 데이터를 가지고 있는 상태
//    |- Error: 서버 데이터 패치 실패 상태

sealed class RootViewState: ViewState() {
    object Initial: RootViewState()
    data class Fetched(val pokemonInfo: PokemonInfo): RootViewState()
    data class Error(val message: String): RootViewState()
}