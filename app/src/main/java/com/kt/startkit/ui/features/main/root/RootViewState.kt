package com.kt.startkit.ui.features.main.root

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.Pokemon
import com.kt.startkit.domain.entity.PokemonInfo

sealed class RootViewState: ViewState() {
    object Initial: RootViewState()
    data class Data(val pokemonInfo: PokemonInfo): RootViewState()
    data class Error(val message: String): RootViewState()
}