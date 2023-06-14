package com.kt.startkit.domain.mapper.berry

import com.kt.startkit.data.model.berry.BerryInfoModel
import com.kt.startkit.domain.entity.berry.BerryInfo
import com.kt.startkit.domain.mapper.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BerryInfoMapper @Inject constructor() : Mapper<BerryInfoModel, BerryInfo> {
    override fun invoke(model: BerryInfoModel): BerryInfo {
        return BerryInfo(
            count = model.count,
            berryNames = model.berryList.map { it.name }
        )
    }
}

