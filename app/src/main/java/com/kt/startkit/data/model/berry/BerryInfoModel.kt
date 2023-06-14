package com.kt.startkit.data.model.berry

import com.squareup.moshi.Json

data class BerryInfoModel (
    @Json(name = "count")
    val count: Int,
    @Json(name = "results")
    val berryList: List<BerryNamedApiModel>,
)
data class BerryNamedApiModel (
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String,
)

