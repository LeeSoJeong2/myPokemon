package com.kt.startkit.di

import com.kt.startkit.data.ApiService
import com.kt.startkit.data.datasource.BerryDataSource
import com.kt.startkit.domain.mapper.berry.BerryDetailMapper
import com.kt.startkit.domain.mapper.berry.BerryInfoMapper
import com.kt.startkit.domain.usecase.BerryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object BerryModule {
//    class BerryUseCase @Inject constructor(
//        private val dataSource: BerryDataSource,
//        private val berryInfoMapper: BerryInfoMapper,
//        private val berryDetailMapper: BerryDetailMapper,
//    )

    @ViewModelScoped
    @Provides
    fun provideBerryDatasource(
        apiService: ApiService,
    ): BerryDataSource {
        return BerryDataSource(apiService)
    }

    @ViewModelScoped
    @Provides
    fun provideBerryUseCase(
        berryDataSource: BerryDataSource,
        berryInfoMapper: BerryInfoMapper,
        berryDetailMapper: BerryDetailMapper,
    ): BerryUseCase {
        return BerryUseCase(berryDataSource, berryInfoMapper, berryDetailMapper)
    }

}
