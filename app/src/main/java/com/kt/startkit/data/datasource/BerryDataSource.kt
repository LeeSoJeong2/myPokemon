package com.kt.startkit.data.datasource

import androidx.compose.ui.unit.Constraints
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
        offset: Int = 0,
        limit: Int = 10,
    ): BerryInfoModel {
        return apiService.getBerryInfo(
            offset = offset,
            limit = limit
        )
    }

    suspend fun getBerryDetail(
        berryName: String,
    ): BerryDetailModel {
        return apiService.getBerryDetail(
            itemName = "$berryName-berry"
        )
    }


}