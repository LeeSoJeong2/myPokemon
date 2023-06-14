package com.kt.startkit.domain.entity

import com.kt.startkit.data.model.NamedApiResourceModel

data class BerriesResponse(
    val next: String?,
    val previous: String?,
    val berriesList: List<NamedApiResourceModel>,
)

