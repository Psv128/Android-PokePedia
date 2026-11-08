package com.example.recetarioapp.validation

object ValidatorPokemonForm {

    fun validarNombre(nombre: String): String? {
        if (nombre.isBlank()) {
            return "El nombre del Pokémon es obligatorio."
        }
        if (nombre.trim().length < 2) {
            return "El nombre debe tener al menos 2 caracteres."
        }
        return null
    }

    fun validarTipo(tipo: String): String? {
        if (tipo.isBlank()) {
            return "Debes seleccionar un tipo de Pokémon."
        }
        return null
    }

    fun validarDescripcion(descripcion: String): String? {
        if (descripcion.isBlank()) {
            return "La descripción es obligatoria."
        }
        if (descripcion.trim().length < 5) {
            return "La descripción debe tener al menos 5 caracteres."
        }
        return null
    }

    fun validarStat(statStr: String, nombreStat: String): String? {
        if (statStr.isBlank()) {
            return "$nombreStat es obligatorio."
        }
        val num = statStr.toIntOrNull()
        if (num == null) {
            return "$nombreStat debe ser un número entero."
        }
        if (num <= 0 || num > 255) {
            return "$nombreStat debe ser un valor entre 1 y 255."
        }
        return null
    }
}
