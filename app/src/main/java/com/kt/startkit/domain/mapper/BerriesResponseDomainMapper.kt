package com.kt.startkit.domain.mapper

import com.kt.startkit.data.model.BerriesResponseModel
import com.kt.startkit.domain.entity.BerriesResponse
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class BerriesResponseDomainMapper @Inject constructor() : Mapper<BerriesResponseModel, BerriesResponse> {
    override fun invoke(model: BerriesResponseModel): BerriesResponse {
        return BerriesResponse(
            next = model.next,
            previous = model.previous,
            berriesList = model.berriesList
        )
    }
}

