package com.example.recetarioapp.navigation

object Rutas {
    const val HOME = "home"
    const val DETALLE = "detalle/{pokemonId}"
    const val COMPARAR = "comparar"
    const val PERFIL = "perfil"
    const val NUEVO_POKEMON = "nuevo_pokemon"
    const val EDITAR_POKEMON = "editar_pokemon/{pokemonId}"

    fun detalleConId(pokemonId: String): String = "detalle/$pokemonId"
    fun editarPokemonConId(pokemonId: String): String = "editar_pokemon/$pokemonId"
}
