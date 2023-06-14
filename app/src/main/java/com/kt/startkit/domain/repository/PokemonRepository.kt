package com.kt.startkit.domain.repository

import com.kt.startkit.core.logger.Logger
import com.kt.startkit.data.datasource.PokemonDataSource
import com.kt.startkit.domain.entity.pokemon.Pokemon
import com.kt.startkit.domain.entity.pokemon.PokemonDetail
import com.kt.startkit.domain.entity.pokemon.PokemonInfo
import com.kt.startkit.domain.mapper.PokemonDetailMapper
import com.kt.startkit.domain.mapper.PokemonInfoMapper
import com.kt.startkit.domain.mapper.PokemonMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonRepository(
    private val dispatcher: CoroutineDispatcher,
    private val dataSource: PokemonDataSource,
    private val pokemonInfoMapper: PokemonInfoMapper,
    private val pokemonMapper: PokemonMapper,
    private val pokemonDetailMapper: PokemonDetailMapper
): Repository {
    private val _pokemonInfo = MutableStateFlow<PokemonInfo?>(null)
    val pokemonInfo = _pokemonInfo.asStateFlow()
    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon = _pokemon.asStateFlow()

    suspend fun fetchPokemonInfo(page: Int = 0, offset: Int = 10) {
        withContext(CoroutineScope(dispatcher + SupervisorJob()).coroutineContext) {
            val result = pokemonInfoMapper(
                dataSource.getPokemonInfo(
                    page = page,
                    pageOffset = offset
                )
            )
            _pokemonInfo.emit(result)
        }
    }

    suspend fun fetchPokemon(name: String) {
        withContext(CoroutineScope(dispatcher + SupervisorJob()).coroutineContext) {
//        CoroutineScope(dispatcher + SupervisorJob()).launch {
            val result = pokemonMapper(dataSource.getPokemonDetail(name))
            _pokemon.emit(result)
        }
    }

    suspend fun fetchPokemonDetail(name: String): PokemonDetail {
        return withContext(CoroutineScope(dispatcher + SupervisorJob()).coroutineContext) {
            pokemonDetailMapper(dataSource.getPokemonDetail(name))
        }
    }

    fun clear() {
        CoroutineScope(dispatcher + SupervisorJob()).launch {
            _pokemonInfo.emit(null)
        }
    }
}