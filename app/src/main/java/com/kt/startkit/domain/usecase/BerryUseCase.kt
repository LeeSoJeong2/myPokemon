package com.kt.startkit.domain.usecase

import com.kt.startkit.data.datasource.BerryDataSource
import com.kt.startkit.data.datasource.ItemDataSource
import com.kt.startkit.domain.entity.Berry
import com.kt.startkit.domain.entity.Item
import com.kt.startkit.domain.mapper.BerryDomainMapper
import com.kt.startkit.domain.mapper.ItemDomainMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BerryUseCase @Inject constructor(
    private val dataSource: BerryDataSource,
    private val domainMapper: BerryDomainMapper,
) : Usecase {

//    var id: String = ""

    suspend fun getBerries(): List<Berry> {
        return dataSource.getBerries().map {
            domainMapper(it)
        }
    }
}