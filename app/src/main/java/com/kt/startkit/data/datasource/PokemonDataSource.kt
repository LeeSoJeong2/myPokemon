package com.kt.startkit.data.datasource

import com.kt.startkit.data.ApiService
import com.kt.startkit.data.model.pokemon.PokemonDetailModel
import com.kt.startkit.data.model.pokemon.PokemonModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDataSource @Inject constructor(
    private val apiService: ApiService
) : DataSource {

    suspend fun getPokemonInfo(page: Int = 0, pageOffset: Int = 10): PokemonModel {
        val offset = pageOffset * page

        return apiService.getPokemon(
            offset = offset,
            limit = pageOffset
        )
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailModel {
        return apiService.getPokemonDetail(name = name)
    }
}