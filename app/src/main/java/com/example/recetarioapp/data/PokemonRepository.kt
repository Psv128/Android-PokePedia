package com.example.recetarioapp.data

import androidx.compose.runtime.mutableStateListOf
import com.example.recetarioapp.model.Pokemon

object PokemonRepository {
    private val _pokemons = mutableStateListOf(
        Pokemon(
            id = "1",
            numero = "#0001",
            nombre = "Bulbasaur",
            tipo = "Planta / Veneno",
            descripcion = "Desde que nace, lleva una semilla en el lomo que crece con él.",
            hp = 45,
            ataque = 49,
            defensa = 49,
            velocidad = 45
        ),
        Pokemon(
            id = "4",
            numero = "#0004",
            nombre = "Charmander",
            tipo = "Fuego",
            descripcion = "La llama de su cola refleja su estado de salud y sus emociones.",
            hp = 39,
            ataque = 52,
            defensa = 43,
            velocidad = 65
        ),
        Pokemon(
            id = "7",
            numero = "#0007",
            nombre = "Squirtle",
            tipo = "Agua",
            descripcion = "Se protege con su caparazón y lanza agua a presión por la boca.",
            hp = 44,
            ataque = 48,
            defensa = 65,
            velocidad = 43
        ),
        Pokemon(
            id = "25",
            numero = "#0025",
            nombre = "Pikachu",
            tipo = "Eléctrico",
            descripcion = "Almacena electricidad en las bolsas de sus mejillas.",
            hp = 35,
            ataque = 55,
            defensa = 40,
            velocidad = 90
        ),
        Pokemon(
            id = "133",
            numero = "#0133",
            nombre = "Eevee",
            tipo = "Normal",
            descripcion = "Su código genético le permite evolucionar de muchas formas.",
            hp = 55,
            ataque = 55,
            defensa = 50,
            velocidad = 55
        )
    )

    val pokemons: List<Pokemon> get() = _pokemons

    fun getPokemonById(id: String): Pokemon? {
        return _pokemons.find { it.id == id || it.numero == id }
    }

    fun addPokemon(pokemon: Pokemon) {
        val nextIdInt = (_pokemons.maxOfOrNull { it.id.toIntOrNull() ?: 0 } ?: 0) + 1
        val newId = nextIdInt.toString()
        val newNumero = "#" + nextIdInt.toString().padStart(4, '0')
        val newPokemon = pokemon.copy(id = newId, numero = newNumero)
        _pokemons.add(newPokemon)
    }

    fun updatePokemon(pokemon: Pokemon) {
        val index = _pokemons.indexOfFirst { it.id == pokemon.id }
        if (index != -1) {
            _pokemons[index] = pokemon
        }
    }
}
