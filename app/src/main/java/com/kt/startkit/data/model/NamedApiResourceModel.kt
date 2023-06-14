package com.kt.startkit.data.model

import com.squareup.moshi.Json

data class NamedApiResourceModel (
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String,
)