package com.kt.startkit.data

import com.kt.startkit.data.model.pokemon.PokemonDetailModel
import com.kt.startkit.data.model.pokemon.PokemonModel
import com.kt.startkit.data.model.berry.BerryDetailModel
import com.kt.startkit.data.model.berry.BerryInfoModel
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.*

interface ApiService {
//    @GET("users")
//    suspend fun getUsers(): List<UserResponse>
//
//    @DELETE("users/{id}")
//    suspend fun remove(@Path("id") userId: String): UserResponse
//
//    @Headers("Content-Type: application/json")
//    @POST("users")
//    suspend fun add(@Body user: UserBody): UserResponse

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

    @GET("item/{name}")
    suspend fun getBerryDetail(
        @Path("name") itemName: String
    ): BerryDetailModel


    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<ApiService>()
    }
}