package com.kt.startkit.ui.features.main.home.detail

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.pokemon.PokemonDetail

// State Hierarchy
//
// PokemonDetailState
//    |- Initial: 초기 상태
//    |- Fetching : Pokemon Detail 를 서버 패치 하고 있는 상태.
//    |- Fetched : Pokemon Detail 정보를 패치 완료한 상태.
//    |- Error : 서버 패치에 실패한 상태.
sealed class PokemonDetailState : ViewState(){
    object Initial : PokemonDetailState()

    object Fetching : PokemonDetailState()

    data class Fetched(val pokemon: PokemonDetail) : PokemonDetailState()

    data class Error(val message: String) : PokemonDetailState()
}