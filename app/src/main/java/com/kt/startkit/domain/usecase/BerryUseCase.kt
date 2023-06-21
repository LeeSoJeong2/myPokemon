package com.kt.startkit.domain.usecase

import com.kt.startkit.data.datasource.BerryDataSource
import com.kt.startkit.domain.entity.berry.BerryDetail
import com.kt.startkit.domain.entity.berry.BerryInfo
import com.kt.startkit.domain.mapper.berry.BerryDetailMapper
import com.kt.startkit.domain.mapper.berry.BerryInfoMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BerryUseCase @Inject constructor(
    private val dataSource: BerryDataSource,
    private val berryInfoMapper: BerryInfoMapper,
    private val berryDetailMapper: BerryDetailMapper,
): Usecase {

    suspend fun fetchBerryInfo(page: Int = 0, limit: Int = 10): BerryInfo {
        return berryInfoMapper(
            dataSource.getBerryInfo(
                offset = page,
                limit = limit
            )
        )
    }

    suspend fun fetchBerryDetail(name: String): BerryDetail {
        return berryDetailMapper(
            dataSource.getBerryDetail(name)
        )
    }

}