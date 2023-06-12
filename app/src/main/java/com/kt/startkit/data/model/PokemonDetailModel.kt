package com.kt.startkit.data.model

import com.squareup.moshi.Json

data class PokemonDetailModel (
    @Json(name = "abilities")
    val abilities: List<PokemonAbilityInfoModel>,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    var id: Int,


)

data class PokemonAbilityInfoModel (
    @Json(name = "ability")
    val ability: PokemonAbilityModel,
    @Json(name = "isHidden")
    val isHidden: Boolean,
    @Json(name = "slot")
    val slot: Int
)

data class PokemonAbilityModel (
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)