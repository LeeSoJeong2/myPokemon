package com.kt.startkit.data.model

import com.squareup.moshi.Json

data class BerriesResponseModel (
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val berriesList: List<NamedApiResourceModel>,
)


