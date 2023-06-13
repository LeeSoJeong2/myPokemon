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
    Grass(Color(0xFF009966)),
    Water(Color(0xFF58ACFA)),
    Bug(Color(0xFF688A08)),
    Electric(Color(0xFFFFCC00)),
    Flying(Color(0xFF6699FF)),
    Normal(Color(0xFFA4A4A4)),
    Ground(Color(0xFF996633)),
    Fairy(Color(0xFFFF99FF)),
    Fighting(Color(0xFFFF9900)),
    Psychic(Color(0xFFCC3366)),
    Rock(Color(0xFF999966)),
    Ghost(Color(0xFF663399)),
    ETC(Color(0xFFA4A4A4));

    companion object {
        fun fromModel(rawValue: String?): PokemonType {
            return when(rawValue) {
                "poison" -> Poison
                "fire" -> Fire
                "grass" -> Grass
                "water" -> Water
                "bug" -> Bug
                "electric" -> Electric
                "flying" -> Flying
                "normal" -> Normal
                "ground" -> Ground
                "fairy" -> Fairy
                "fighting" -> Fighting
                "psychic" -> Psychic
                "rock" -> Rock
                "ghost" -> Ghost
                else -> ETC
            }
        }
    }
}