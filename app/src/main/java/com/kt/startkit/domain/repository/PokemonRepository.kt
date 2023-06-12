package com.kt.startkit.domain.repository

import com.kt.startkit.data.datasource.PokemonDataSource
import com.kt.startkit.domain.entity.PokemonInfo
import com.kt.startkit.domain.mapper.PokemonMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonRepository(
    private val dispatcher: CoroutineDispatcher,
    private val dataSource: PokemonDataSource,
    private val mapper: PokemonMapper
): Repository {
    private val _pokemonInfo = MutableStateFlow<PokemonInfo?>(null)
    val pokemonInfo = _pokemonInfo.asStateFlow()

    suspend fun fetchPokemon(page: Int = 0) {
        CoroutineScope(dispatcher + SupervisorJob()).async {
            val result = dataSource.getPokemon(page = page)
            _pokemonInfo.emit(mapper(result))
        }.await()
    }

    fun clear() {
        CoroutineScope(dispatcher + SupervisorJob()).launch {
            _pokemonInfo.emit(null)
        }
    }
}