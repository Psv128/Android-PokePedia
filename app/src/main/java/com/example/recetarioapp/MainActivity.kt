package com.example.recetarioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recetarioapp.navigation.Rutas
import com.example.recetarioapp.ui.navigation.PokemonBottomBar
import com.example.recetarioapp.ui.navigation.PokemonNavGraph
import com.example.recetarioapp.ui.navigation.PokemonTopAppBar
import com.example.recetarioapp.ui.theme.PokemonWikiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonWikiTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val canNavigateBack = (navController.previousBackStackEntry != null) && (currentRoute != Rutas.HOME)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        PokemonTopAppBar(
                            currentRoute = currentRoute,
                            canNavigateBack = canNavigateBack,
                            onBackClick = { navController.popBackStack() }
                        )
                    },
                    bottomBar = {
                        PokemonBottomBar(
                            currentRoute = currentRoute,
                            onNavigateToRoute = { route ->
                                if (currentRoute != route) {
                                    navController.navigate(route) {
                                        popUpTo(Rutas.HOME) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    PokemonNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
