package com.kt.startkit.ui.features.main.home

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.PokemonInfo

sealed class HomeState: ViewState() {
    object Initial: HomeState()
    object Loading: HomeState()
    data class Data(val pokemonInfo: PokemonInfo): HomeState()
    data class Error(val message: String): HomeState()
}