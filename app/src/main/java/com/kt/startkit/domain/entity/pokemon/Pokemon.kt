package com.kt.startkit.domain.entity.pokemon

import androidx.compose.ui.graphics.Color

data class Pokemon (
    val name: String,
    val id: Int,
    val thumbnail: String?,
    val type: PokemonType
)

enum class PokemonType(val color: Color) {
    Poison(Color(0xFF9A2EFE)),
    Fire(Color(0xFFFA5858)),
    Grass(Color(0xFF04B45F)),
    Water(Color(0xFF58ACFA)),
    Bug(Color(0xFF688A08)),
    ETC(Color(0xFFA4A4A4));

    companion object {
        fun fromModel(rawValue: String?): PokemonType {
            return when(rawValue) {
                "poison" -> Poison
                "fire" -> Fire
                "grass" -> Grass
                "water" -> Water
                "bug" -> Bug
                else -> ETC
            }
        }
    }
}