package com.kt.startkit.domain.mapper

import com.kt.startkit.data.model.BerryModel
import com.kt.startkit.domain.entity.Berry
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BerryDomainMapper @Inject constructor() : Mapper<BerryModel, Berry> {
    override fun invoke(model: BerryModel): Berry {
        return Berry(
            id = model.id,
            name = model.name,
            imageUrl = model.sprites.default,
            effect = model.effects.first().effect
        )
    }
}

