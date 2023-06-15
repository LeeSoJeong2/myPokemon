package com.kt.startkit.di

import com.kt.startkit.core.datastore.PreferenceDataStore
import com.kt.startkit.data.datasource.BerryDataSource
import com.kt.startkit.data.datasource.PokemonDataSource
import com.kt.startkit.domain.mapper.PokemonDetailMapper
import com.kt.startkit.domain.mapper.PokemonInfoMapper
import com.kt.startkit.domain.mapper.PokemonMapper
import com.kt.startkit.domain.mapper.berry.BerryDetailMapper
import com.kt.startkit.domain.mapper.berry.BerryInfoMapper
import com.kt.startkit.domain.repository.BerryRepository
import com.kt.startkit.domain.repository.PokemonRepository
import com.kt.startkit.domain.repository.UserProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Singleton
    @Provides
    fun provideUserProfileRepository(
        preferences: PreferenceDataStore,
        @AppDispatchers(AppCoroutineDispatchers.IO) dispatcher: CoroutineDispatcher,
    ): UserProfileRepository {
        return UserProfileRepository(
            preferences = preferences,
            dispatcher = dispatcher
        )
    }

    @Singleton
    @Provides
    fun providePokemonRepository(
        @AppDispatchers(AppCoroutineDispatchers.IO) dispatcher: CoroutineDispatcher,
        dataSource: PokemonDataSource,
        pokemonInfoMapper: PokemonInfoMapper,
        pokemonMapper: PokemonMapper,
        pokemonDetailMapper: PokemonDetailMapper
    ): PokemonRepository {
        return PokemonRepository(
            dispatcher = dispatcher,
            dataSource = dataSource,
            pokemonInfoMapper = pokemonInfoMapper,
            pokemonMapper = pokemonMapper,
            pokemonDetailMapper = pokemonDetailMapper
        )
    }

    @Provides
    fun provideBerryRepository(
        dataSource: BerryDataSource,
        berryInfoMapper: BerryInfoMapper,
        berryDetailMapper: BerryDetailMapper,
    ): BerryRepository {
        return BerryRepository(
            dataSource = dataSource,
            berryInfoMapper = berryInfoMapper,
            berryDetailMapper = berryDetailMapper
        )
    }
}
