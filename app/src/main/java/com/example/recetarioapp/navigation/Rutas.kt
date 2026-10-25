package com.example.recetarioapp.navigation

object Rutas {
    const val HOME = "home"
    const val DETALLE = "detalle/{pokemonId}"
    const val COMPARAR = "comparar"
    const val PERFIL = "perfil"

    fun detalleConId(pokemonId: String): String = "detalle/$pokemonId"
}
