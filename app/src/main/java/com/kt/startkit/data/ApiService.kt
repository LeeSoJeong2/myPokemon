package com.kt.startkit.data

import com.kt.startkit.data.model.pokemon.PokemonDetailModel
import com.kt.startkit.data.model.pokemon.PokemonModel
import com.kt.startkit.data.model.berry.BerryDetailModel
import com.kt.startkit.data.model.berry.BerryInfoModel
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

    @GET("berry")
    suspend fun getBerryInfo(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): BerryInfoModel

    @GET("item/{id}")
    suspend fun getBerryDetail(
        @Path("id") itemId: Int
    ): BerryDetailModel


    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<ApiService>()
    }
}