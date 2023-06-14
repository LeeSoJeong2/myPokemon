package com.kt.startkit.data.model.berry

import com.squareup.moshi.Json

data class BerryDetailModel (
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "effect_entries")
    val effects: List<EffectModel>,
    @Json(name = "sprites")
    val sprites: ItemSpritesModel,
)
data class EffectModel (
    @Json(name = "effect")
    val effect: String,
)
data class ItemSpritesModel (
    @Json(name = "default")
    val default: String,
)

