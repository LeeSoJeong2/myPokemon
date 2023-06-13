package com.kt.startkit.domain.entity.pokemon

import com.kt.startkit.data.model.pokemon.PokemonStatInfoModel
import java.util.Dictionary

data class PokemonDetail (
        val ability: List<PokemonAbility>,
        val height: Int,
        val id: Int,
        val name: String,
//        val order: Int,
        val images: List<String>,
        val stats: Map<PokemonStatType, PokemonStat>,
        val weight: Int,
        val type: PokemonType
)

data class PokemonAbility(
        val name: String,
        val isHidden: Boolean,
        val slot: Int,
)

enum class PokemonStatType {
        HP,
        Attack,
        Defense,
        SpecialAttack,
        SpecialDefense,
        Speed,
        Unknown
        ;
        companion object {
                fun fromModel(model: PokemonStatInfoModel): Pair<PokemonStatType, PokemonStat> {
                        return when(model.stat.name) {
                                "hp" -> HP to PokemonStat(model.effort, model.baseStat)
                                "attack" -> Attack to PokemonStat(model.effort, model.baseStat)
                                "defense" -> Defense to PokemonStat(model.effort, model.baseStat)
                                "special-attack" -> SpecialAttack to PokemonStat(model.effort, model.baseStat)
                                "special_defense" -> SpecialDefense to PokemonStat(model.effort, model.baseStat)
                                "speed" -> Speed to PokemonStat(model.effort, model.baseStat)
                                else -> Unknown to PokemonStat(model.effort, model.baseStat)

                        }
                }
        }
}

data class PokemonStat (
        val effort: Int,
        val baseStat: Int,
)