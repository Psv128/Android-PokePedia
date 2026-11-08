package com.example.recetarioapp.validator

object PokemonFormValidator {

    fun validarNombre(nombre: String): String? {
        if (nombre.isBlank()) {
            return "El nombre del Pokémon no puede estar vacío."
        }
        if (nombre.trim().length < 2) {
            return "El nombre debe tener al menos 2 caracteres."
        }
        return null
    }

    fun validarNumero(numero: String): String? {
        if (numero.isBlank()) {
            return "El número de Pokédex es obligatorio (ej: #0025)."
        }
        return null
    }

    fun validarTipo(tipo: String): String? {
        if (tipo.isBlank()) {
            return "Debe seleccionar un tipo para el Pokémon."
        }
        return null
    }

    fun validarStat(valorText: String, nombreStat: String): String? {
        if (valorText.isBlank()) {
            return "El campo $nombreStat es obligatorio."
        }
        val valor = valorText.toIntOrNull() ?: return "El valor de $nombreStat debe ser un número entero."
        if (valor <= 0) {
            return "El valor de $nombreStat debe ser mayor que 0."
        }
        if (valor > 255) {
            return "El valor de $nombreStat no puede superar 255."
        }
        return null
    }

    fun validarDescripcion(descripcion: String): String? {
        if (descripcion.isBlank()) {
            return "La descripción no puede estar vacía."
        }
        return null
    }
}
