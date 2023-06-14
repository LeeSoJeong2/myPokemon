package com.kt.startkit.ui.features.onboarding.step.find_pokemon.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.kt.startkit.ui.features.onboarding.step.find_pokemon.FindPokemonViewModel

@Composable
fun RoutineView(
    findPokemonViewModel: FindPokemonViewModel,
) {
    Column {
        Text("루틴을 골라주세요")
        Row {
            Button(onClick = {
                findPokemonViewModel.increase(1)
            }) {
                Text(text = "퇴근하기")
            }
            Button(onClick = {
                findPokemonViewModel.increase(3)
            }) {
                Text(text = "야근하기")
            }
        }
    }

}
