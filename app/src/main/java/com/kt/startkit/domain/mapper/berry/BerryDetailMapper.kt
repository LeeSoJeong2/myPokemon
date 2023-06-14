package com.kt.startkit.domain.mapper.berry

import com.kt.startkit.data.model.berry.BerryDetailModel
import com.kt.startkit.domain.entity.berry.BerryDetail
import com.kt.startkit.domain.mapper.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BerryDetailMapper @Inject constructor() : Mapper<BerryDetailModel, BerryDetail> {
    override fun invoke(model: BerryDetailModel): BerryDetail {
        return BerryDetail(
            id = model.id,
            name = model.name,
            effect = model.effects.first().effect,
            imageUrl = model.sprites.default,
        )
    }
}

