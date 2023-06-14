package com.kt.startkit.ui.features.main.home

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.pokemon.Pokemon
import com.kt.startkit.domain.entity.pokemon.PokemonInfo

abstract class HaveCurrentPage(open val currentPage: Int): ViewState()
sealed class HomeState(currentPage: Int) : HaveCurrentPage(currentPage) {
    data class Initial(override val currentPage: Int) : HomeState(currentPage)
//    object Loading: HomeState()
    data class Data(
        override val currentPage: Int,
        val pokemonInfo: PokemonInfo
    ) : HomeState(currentPage)
    data class Updated(
        val totalCount: Int,
        override val currentPage: Int,
        val pokemonList: List<Pokemon>
    ) : HomeState(currentPage)

    data class Updating(
        override val currentPage: Int
    ) : HomeState(currentPage)
    data class Error(
        override val currentPage: Int,
        val message: String
    ) : HomeState(currentPage)
}