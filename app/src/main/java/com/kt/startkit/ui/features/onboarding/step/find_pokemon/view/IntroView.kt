package com.kt.startkit.ui.features.onboarding.step.find_pokemon.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel

@Composable
fun IntroView(
    findPokemonViewModel: FindPokemonViewModel,
) {
    Column {
        Text("안녕하세요! 환영합니다!!!!!")
    }
}
