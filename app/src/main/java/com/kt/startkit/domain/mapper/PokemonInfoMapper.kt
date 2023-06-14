package com.kt.startkit.domain.mapper

import com.kt.startkit.data.model.pokemon.PokemonDetailModel
import com.kt.startkit.data.model.pokemon.PokemonModel
import com.kt.startkit.domain.entity.pokemon.Pokemon
import com.kt.startkit.domain.entity.pokemon.PokemonAbility
import com.kt.startkit.domain.entity.pokemon.PokemonDetail
import com.kt.startkit.domain.entity.pokemon.PokemonInfo
import com.kt.startkit.domain.entity.pokemon.PokemonStat
import com.kt.startkit.domain.entity.pokemon.PokemonStatType
import com.kt.startkit.domain.entity.pokemon.PokemonType
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonInfoMapper @Inject constructor() : Mapper<PokemonModel, PokemonInfo> {
    override fun invoke(model: PokemonModel): PokemonInfo {
        return PokemonInfo(
           count = model.count,
           names = model.results.map { it.name }
       )
    }
}

@Singleton
class PokemonMapper @Inject constructor() : Mapper<PokemonDetailModel, Pokemon> {
    override fun invoke(model: PokemonDetailModel): Pokemon {
        return Pokemon(
            name = model.name,
            id = model.id,
            thumbnail = model.sprites.frontDefault,
            type = PokemonType.fromModel(model.types.firstOrNull()?.type?.name)
        )
    }
}

@Singleton
class PokemonDetailMapper @Inject constructor(): Mapper<PokemonDetailModel, PokemonDetail> {
    override fun invoke(model: PokemonDetailModel): PokemonDetail {
        val stats = mutableMapOf<PokemonStatType, PokemonStat>()

        model.stats.forEach {
            val pair = PokemonStatType.fromModel(it)
            stats[pair.first] = pair.second
        }

        return PokemonDetail(
            ability = model.abilities.map {
                PokemonAbility(
                    name = it.ability.name,
                    isHidden = it.isHidden,
                    slot = it.slot
                )
            },
            height = model.height,
            id = model.id,
            name = model.name,
            images = model.sprites.toList(),
            stats = stats,
            weight = model.weight,
            type = PokemonType.fromModel(model.types.firstOrNull()?.type?.name),
            thumbnail = model.sprites.frontDefault
        )
    }

}

