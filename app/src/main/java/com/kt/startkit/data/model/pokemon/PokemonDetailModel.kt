package com.kt.startkit.data.model.pokemon

import com.squareup.moshi.Json

data class PokemonDetailModel (
    @Json(name = "abilities")
    val abilities: List<PokemonAbilityInfoModel>,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int,
    @Json(name = "sprites")
    val sprites: PokemonSpritesModel,
    @Json(name = "stats")
    val stats: List<PokemonStatInfoModel>,
    @Json(name = "weight")
    val weight: Int,
    @Json(name = "types")
    val types: List<PokemonTypeInfoModel>
)

data class PokemonAbilityInfoModel (
    @Json(name = "ability")
    val ability: PokemonAbilityModel,
    @Json(name = "is_hidden")
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

data class PokemonSpritesModel (
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_female")
    val backFemale: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "back_shiny_female")
    val backShinyFemale: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
) {
    fun toList(): List<String> {
        val names = mutableListOf<String>()

        if (frontDefault != null) {
            names.add(frontDefault)
        }

        if (frontShiny != null) {
            names.add(frontShiny)
        }

        if (backDefault != null) {
            names.add(backDefault)
        }

        if (backShiny != null) {
            names.add(backShiny)
        }


        if (frontFemale != null) {
            names.add(frontFemale)
        }

        if (frontShinyFemale != null) {
            names.add(frontShinyFemale)
        }

        if (backFemale != null) {
            names.add(backFemale)
        }

        if (backShinyFemale != null) {
            names.add(backShinyFemale)
        }

        return names
    }
}

data class PokemonStatInfoModel(
    @Json(name = "base_stat")
    val baseStat: Int,
    @Json(name = "effort")
    val effort: Int,
    @Json(name = "stat")
    val stat: PokemonStatModel
)

data class PokemonStatModel(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String,
)

data class PokemonTypeInfoModel(
    @Json(name = "slot")
    val slot: Int,
    @Json(name = "type")
    val type: PokemonTypeModel
)

data class PokemonTypeModel(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)