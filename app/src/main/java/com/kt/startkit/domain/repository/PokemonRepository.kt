package com.kt.startkit.domain.repository

import com.kt.startkit.data.datasource.PokemonDataSource
import com.kt.startkit.di.AppCoroutineDispatchers
import com.kt.startkit.di.AppDispatchers
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
import javax.inject.Singleton

class PokemonRepository(
    private val dispatcher: CoroutineDispatcher,
    private val dataSource: PokemonDataSource,
    private val pokemonInfoMapper: PokemonInfoMapper,
    private val pokemonMapper: PokemonMapper,
    private val pokemonDetailMapper: PokemonDetailMapper
): Repository {
    // PokemonInfo -> 현재 page 의 names list 를 가지고 있음.
    private val _pokemonInfo = MutableStateFlow<PokemonInfo?>(null)
    val pokemonInfo = _pokemonInfo.asStateFlow()

    // Pokemon
    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon = _pokemon.asStateFlow()


    // PokemonInfo 를 받아와 _pokemonInfo Flow 에 emit.
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

    // 해당 name 의 Pokemon 를 받아와 _pokemon Flow 에 emit.
    suspend fun fetchPokemon(name: String) {
        // Pokemon 정보를 받아 list 에 추가 할 때, thread 관리가 어려워 await 로 만듦.
        withContext(CoroutineScope(dispatcher + SupervisorJob()).coroutineContext) {
            val result = pokemonMapper(dataSource.getPokemonDetail(name))
            _pokemon.emit(result)
        }
    }

    // 해당 name 의 Pokemon Detail 를 받아와 return.
    suspend fun getPokemonDetail(name: String): PokemonDetail {
        return withContext(CoroutineScope(dispatcher + SupervisorJob()).coroutineContext) {
            pokemonDetailMapper(dataSource.getPokemonDetail(name))
        }
    }

    // 해당 name 의 Pokemon 를 받아와 return.
    suspend fun getPokemon(name: String): Pokemon {
        return withContext(CoroutineScope(dispatcher + SupervisorJob()).coroutineContext) {
            pokemonMapper(dataSource.getPokemonDetail(name))
        }
    }

    fun clear() {
        CoroutineScope(dispatcher + SupervisorJob()).launch {
            _pokemonInfo.emit(null)
        }
    }
}