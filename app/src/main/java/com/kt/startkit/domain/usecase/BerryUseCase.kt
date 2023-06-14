package com.kt.startkit.domain.usecase

import com.kt.startkit.data.datasource.BerryDataSource
import com.kt.startkit.domain.entity.berry.BerryDetail
import com.kt.startkit.domain.entity.berry.BerryInfo
import com.kt.startkit.domain.mapper.berry.BerryDetailMapper
import com.kt.startkit.domain.mapper.berry.BerryInfoMapper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@ViewModelScoped
class BerryUseCase @Inject constructor(
    private val dataSource: BerryDataSource,
    private val berryInfoMapper: BerryInfoMapper,
    private val berryDetailMapper: BerryDetailMapper,
) : Usecase {


    // TODO: coroutinescope 으로 변경
    suspend fun getBerryInfo(): BerryInfo {
        return berryInfoMapper(dataSource.getBerryInfo())
    }

    // TODO: coroutinescope 으로 변경
    suspend fun getBerryDetail(
        berryName: String
    ): BerryDetail {
        return berryDetailMapper(dataSource.getBerryDetail(berryName))
    }

}