package com.kt.startkit.domain.mapper

import com.kt.startkit.data.model.PokemonModel
import com.kt.startkit.domain.entity.Pokemon
import com.kt.startkit.domain.entity.PokemonInfo
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonMapper @Inject constructor() : Mapper<PokemonModel, PokemonInfo> {
    override fun invoke(model: PokemonModel): PokemonInfo {
        val results = mutableListOf<Pokemon>()

        model.results.forEach {
            val id = Pokemon.parseUrl(it.url)
            if (id != null) {
                results.add(Pokemon(
                    name = it.name,
                    id = id))
            }
        }

        return PokemonInfo(
           count = model.count,
           results = results
       )
    }
}