package com.kt.startkit.data.datasource

import com.kt.startkit.core.logger.Logger
import com.kt.startkit.data.ApiService
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

    suspend fun getBerries(
        startBerryId: Int = 126,
        indexNumber: Int = 10,
    ): List<BerryModel> {
        return (0 until indexNumber).map { index ->

            getBerryDetailAsync(startBerryId + index).await()

        }
    }

    private suspend fun getBerryDetailAsync(
        berryId: Int
    ): Deferred<BerryModel> = CoroutineScope(Dispatchers.IO).async {
        Logger.d("getBerries id : $berryId")
        apiService.getBerryDetail(berryId)
    }
//        apiService.getBerryDetail(berryId)


}