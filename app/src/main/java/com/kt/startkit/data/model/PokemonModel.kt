package com.kt.startkit.data.model

import com.squareup.moshi.Json

data class PokemonModel (
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val nextUrl: String?,
    @Json(name = "previous")
    val previousUrl: String?,
    @Json(name = "results")
    val results: List<PokemonResultModel>
)

data class PokemonResultModel (
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)