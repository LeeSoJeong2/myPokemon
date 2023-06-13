package com.kt.startkit.data.model

import com.squareup.moshi.Json

data class BerryModel (
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "effect_entries")
    val effects: List<VerboseEffect>,
    @Json(name = "sprites")
    val sprites: ItemSprites,
)
data class VerboseEffect (
    @Json(name = "effect")
    val effect: String,
)
data class ItemSprites (
    @Json(name = "default")
    val default: String,
)

