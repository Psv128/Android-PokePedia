package com.example.recetarioapp.model

data class Pokemon(
    val id: String,
    val numero: String,
    val nombre: String,
    val tipo: String,
    val descripcion: String,
    val hp: Int,
    val ataque: Int,
    val defensa: Int,
    val velocidad: Int
)
