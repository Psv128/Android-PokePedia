package com.example.recetarioapp.navigation

object Rutas {
    const val HOME = "home"
    const val DETALLE = "detalle/{pokemonId}"
    const val COMPARAR = "comparar"
    const val PERFIL = "perfil"
    const val CREAR = "crear"
    const val EDITAR = "editar/{pokemonId}"

    fun detalleConId(pokemonId: String): String = "detalle/$pokemonId"
    fun editarConId(pokemonId: String): String = "editar/$pokemonId"
}
