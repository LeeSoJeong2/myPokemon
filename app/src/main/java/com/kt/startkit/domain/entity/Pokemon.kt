package com.kt.startkit.domain.entity

data class PokemonInfo (
    val count: Int,
    val results: List<Pokemon>
)

data class Pokemon (
    val name: String,
    val id: String
) {
    companion object {
        fun parseUrl(url: String): String? {
            val paths = url.split("/")
            return if (paths.size >= 2) {
                paths[paths.size - 2]
            } else {
                null
            }
        }
    }
}