package com.kt.startkit.domain.usecase

import com.kt.startkit.data.datasource.on_boarding.FindPokemonDatasource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FindPokemonUseCase @Inject constructor(
    val dataSource: FindPokemonDatasource
): Usecase {

}
