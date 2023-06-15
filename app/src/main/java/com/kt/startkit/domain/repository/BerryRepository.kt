package com.kt.startkit.domain.repository

import com.kt.startkit.data.datasource.BerryDataSource
import com.kt.startkit.domain.entity.berry.BerryDetail
import com.kt.startkit.domain.entity.berry.BerryInfo
import com.kt.startkit.domain.mapper.berry.BerryDetailMapper
import com.kt.startkit.domain.mapper.berry.BerryInfoMapper

class BerryRepository(
    private val dataSource: BerryDataSource,
    private val berryInfoMapper: BerryInfoMapper,
    private val berryDetailMapper: BerryDetailMapper,
    ): Repository {

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