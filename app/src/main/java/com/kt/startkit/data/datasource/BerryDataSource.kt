package com.kt.startkit.data.datasource

import com.kt.startkit.data.ApiService
import com.kt.startkit.data.model.berry.BerryDetailModel
import com.kt.startkit.data.model.berry.BerryInfoModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BerryDataSource @Inject constructor(
    private val apiService: ApiService,
) : DataSource {

    suspend fun getBerryInfo(
        offset: Int? = null,
        limit: Int? = 10,
    ): BerryInfoModel {
        return apiService.getBerryInfo(
            offset = offset,
            limit = limit
        )
    }

    suspend fun getBerryDetail(
        berryId: Int = 1
    ): BerryDetailModel {
        return apiService.getBerryDetail(
            itemId = 125 + berryId
        )
    }


}