package com.kt.startkit.data

import com.kt.startkit.data.model.pokemon.PokemonDetailModel
import com.kt.startkit.data.model.pokemon.PokemonModel
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.*

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonModel

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetailModel

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<ApiService>()
    }
}