package com.kt.startkit.domain.usecase

import com.kt.startkit.data.datasource.BerryDataSource
import com.kt.startkit.domain.entity.BerriesResponse
import com.kt.startkit.domain.mapper.BerriesResponseDomainMapper
import com.kt.startkit.domain.mapper.BerryDomainMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BerryUseCase @Inject constructor(
    private val dataSource: BerryDataSource,
    private val berriesResponseDomainMapper: BerriesResponseDomainMapper,
    private val berryDomainMapper: BerryDomainMapper,
) : Usecase {

//    var id: String = ""

    suspend fun getBerriesResponse(): BerriesResponse {
        return berriesResponseDomainMapper(dataSource.getBerriesList())
    }

//    suspend fun getItems(): List<Item> {
//        return dataSource.get.map {
//            berryDomainMapper(it)
//        }
//    }
}