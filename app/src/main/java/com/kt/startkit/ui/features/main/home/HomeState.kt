package com.kt.startkit.ui.features.main.home

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.pokemon.Pokemon
import com.kt.startkit.domain.entity.pokemon.PokemonInfo

// State Hierarchy
//
// HomeState
//    |- Initial: 초기 상태
//    |- PokemonInfoFetched : PokemonInfo(current page 의 포켓몬 name list) 서버 패치를 완료한 상태
//    |- PokemonListFetched : PokemonList 패치를 완료한 상태
//    |- Updating : current page 가 변경 되어 PokemonInfo 정보를 가져 오고 있는 상태
//    |- Error : PokemonInfo 서버 패치에 실패한 상태.

sealed class HomeState : ViewState() {
    abstract val currentPage: Int

    data class Initial(override val currentPage: Int) : HomeState()

    data class PokemonInfoFetched(
        override val currentPage: Int,
        val pokemonInfo: PokemonInfo
    ) : HomeState()

    data class PokemonListFetched(
        val totalCount: Int,
        override val currentPage: Int,
        val pokemonList: List<Pokemon>
    ) : HomeState()

    data class Fetching(
        override val currentPage: Int
    ) : HomeState()

    data class Error(
        override val currentPage: Int,
        val message: String
    ) : HomeState()
}