package com.kt.startkit.data.datasource

import com.kt.startkit.data.ApiService
import com.kt.startkit.data.model.PokemonModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDataSource @Inject constructor(
    private val apiService: ApiService
) : DataSource {
    companion object {
        const val paging = 10
    }

    suspend fun getPokemon(page: Int = 0): PokemonModel {
        val offset = page * paging
        val limit = page * (paging + 1)

        return apiService.getPokemon(
            offset = offset,
            limit = limit
        )
    }

//    suspend fun g
}