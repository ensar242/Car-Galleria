package com.example.cargalleria.navigaion

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cargalleria.ViewModel.CarViewModel
import com.example.cargalleria.data.Car
import com.example.cargalleria.ui.theme.screens.AddCarScreen
import com.example.cargalleria.ui.theme.screens.DetailScreen
import com.example.cargalleria.ui.theme.screens.FavoritesScreen
import com.example.cargalleria.ui.theme.screens.HomeScreen
import com.example.cargalleria.ui.theme.screens.SettingsScreen
import com.google.gson.Gson


@Composable
fun AppNavigations() {
    val navController: NavHostController = rememberNavController()
    val carViewModel: CarViewModel = viewModel() // Shared ViewModel

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
                val currentDestination: NavDestination? = navBackStackEntry?.destination
                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Screens.HomeScreen.name, modifier = Modifier.padding(paddingValues)) {
            composable(route = Screens.HomeScreen.name) {
                HomeScreen(carViewModel, navigateToDetail = { car ->
                    val carJson = Uri.encode(Gson().toJson(car))
                    navController.navigate("${Screens.DetailScreen.name}/$carJson")
                })
            }
            composable(route = Screens.AddCarScreen.name) {
                AddCarScreen(carViewModel, navController)
            }
            composable(route = "${Screens.DetailScreen.name}/{car}") { backStackEntry ->
                val carJson = backStackEntry.arguments?.getString("car")
                val car = Gson().fromJson(carJson, Car::class.java)
                DetailScreen(car, navController)
            }
            composable(route = Screens.FavoritesScreen.name) {
                FavoritesScreen()
            }
            composable(route = Screens.SettingScreen.name) {
                SettingsScreen()
            }
        }
    }
}
