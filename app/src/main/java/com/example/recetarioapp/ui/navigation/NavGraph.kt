package com.example.recetarioapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recetarioapp.data.PokemonRepository
import com.example.recetarioapp.navigation.Rutas
import com.example.recetarioapp.ui.screens.CompararScreen
import com.example.recetarioapp.ui.screens.DetalleScreen
import com.example.recetarioapp.ui.screens.HomeScreen
import com.example.recetarioapp.ui.screens.PerfilScreen
import com.example.recetarioapp.ui.screens.PokemonFormScreen

@Composable
fun PokemonNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Rutas.HOME,
        modifier = modifier
    ) {
        composable(Rutas.HOME) {
            HomeScreen(
                pokemons = PokemonRepository.pokemons,
                onPokemonClick = { pokemonId ->
                    navController.navigate(Rutas.detalleConId(pokemonId))
                },
                onGoToCompare = {
                    navController.navigate(Rutas.COMPARAR)
                },
                onAddPokemonClick = {
                    navController.navigate(Rutas.NUEVO_POKEMON)
                }
            )
        }

        composable(
            route = Rutas.DETALLE,
            arguments = listOf(
                navArgument("pokemonId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: ""
            DetalleScreen(
                pokemonId = pokemonId,
                onCompareClick = {
                    navController.navigate(Rutas.COMPARAR)
                },
                onEditClick = { id ->
                    navController.navigate(Rutas.editarPokemonConId(id))
                }
            )
        }

        composable(Rutas.COMPARAR) {
            CompararScreen(
                onPokemonClick = { pokemonId ->
                    navController.navigate(Rutas.detalleConId(pokemonId))
                }
            )
        }

        composable(Rutas.PERFIL) {
            PerfilScreen()
        }

        composable(Rutas.NUEVO_POKEMON) {
            PokemonFormScreen(
                pokemonInicial = null,
                onSaveClick = { nuevoPokemon ->
                    PokemonRepository.addPokemon(nuevoPokemon)
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Rutas.EDITAR_POKEMON,
            arguments = listOf(
                navArgument("pokemonId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId") ?: ""
            val pokemonAEditar = PokemonRepository.getPokemonById(pokemonId)
            PokemonFormScreen(
                pokemonInicial = pokemonAEditar,
                onSaveClick = { pokemonEditado ->
                    PokemonRepository.updatePokemon(pokemonEditado)
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
