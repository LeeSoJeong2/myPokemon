package com.kt.startkit.data

import com.kt.startkit.data.model.PokemonModel
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
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonModel

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String):

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<ApiService>()
    }
}