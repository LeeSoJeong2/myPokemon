package com.kt.startkit.data.datasource

import com.kt.startkit.core.logger.Logger
import com.kt.startkit.data.ApiService
import com.kt.startkit.data.model.BerriesResponseModel
import com.kt.startkit.data.model.BerryModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@ViewModelScoped
class BerryDataSource @Inject constructor(
    private val apiService: ApiService,
) : DataSource {

    suspend fun getBerriesList(
        offset: Int? = null,
        limit: Int? = 10,
    ): BerriesResponseModel {
        return apiService.getBerriesList(
            offset = offset,
            limit = limit
        )
    }

//    private suspend fun getBerryDetail(
//        berryId: Int
//    ): Deferred<BerryModel> {
//        Logger.d("getBerries id : $berryId")
//        apiService.getBerryDetail(berryId)
//    }


}