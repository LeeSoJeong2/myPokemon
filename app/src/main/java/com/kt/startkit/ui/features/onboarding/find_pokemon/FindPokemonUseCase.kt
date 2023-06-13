package com.kt.startkit.ui.features.onboarding.find_pokemon

import com.kt.startkit.domain.usecase.Usecase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FindPokemonUseCase @Inject constructor(
    val dataSource: FindPokemonDatasource
): Usecase {

}
