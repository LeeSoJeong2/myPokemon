package com.kt.startkit.ui.features.main.home.detail

import com.kt.startkit.core.base.ViewState
import com.kt.startkit.domain.entity.pokemon.PokemonDetail

sealed class PokemonDetailState : ViewState(){
    object Initial : PokemonDetailState()

    data class Loading(val name: String) : PokemonDetailState()

    data class Data(val pokemon: PokemonDetail) : PokemonDetailState()

    data class Error(val message: String) : PokemonDetailState()
}