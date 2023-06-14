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
) : Usecase {

//    var id: String = ""

    suspend fun getBerryInfo(): BerryInfo {
        return berryInfoMapper(dataSource.getBerryInfo())
    }

    suspend fun getBerryDetail(
        berryPage: Int,
        berryIndex: Int,
    ): BerryDetail {
        return berryDetailMapper(dataSource.getBerryDetail(berryPage, berryIndex))
    }

}