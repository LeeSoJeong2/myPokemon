package com.kt.startkit.domain.usecase

import com.kt.startkit.data.datasource.PokemonDataSource
import com.kt.startkit.di.AppCoroutineDispatchers
import com.kt.startkit.di.AppDispatchers
import com.kt.startkit.domain.entity.pokemon.PokemonDetail
import com.kt.startkit.domain.mapper.PokemonDetailMapper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class PokemonUseCase @Inject constructor(
    @AppDispatchers(AppCoroutineDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val dataSource: PokemonDataSource,
    private val pokemonDetailMapper: PokemonDetailMapper,
) : Usecase {

    suspend fun fetchPokemonDetail(name: String): PokemonDetail {
        return withContext(CoroutineScope(dispatcher + SupervisorJob()).coroutineContext) {
            pokemonDetailMapper(dataSource.getPokemonDetail(name))
        }
    }
}