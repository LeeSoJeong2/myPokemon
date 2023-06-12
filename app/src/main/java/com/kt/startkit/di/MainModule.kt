package com.kt.startkit.di

import com.kt.startkit.core.datastore.PreferenceDataStore
import com.kt.startkit.data.datasource.PokemonDataSource
import com.kt.startkit.domain.mapper.PokemonMapper
import com.kt.startkit.domain.repository.PokemonRepository
import com.kt.startkit.domain.repository.UserProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        mapper: PokemonMapper,
    ): PokemonRepository {
        return PokemonRepository(
            dispatcher = dispatcher,
            dataSource = dataSource,
            mapper = mapper
        )
    }
}
