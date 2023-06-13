package com.kt.startkit.ui.features.main.home

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.pokemon.Pokemon
import com.kt.startkit.domain.entity.pokemon.PokemonInfo

sealed class HomeState: ViewState() {
    object Initial: HomeState()
//    object Loading: HomeState()
    data class Data(val pokemonInfo: PokemonInfo): HomeState()
    data class Updated(val pokemonList: List<Pokemon>): HomeState()
    data class Error(val message: String): HomeState()
}