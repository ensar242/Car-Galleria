package com.example.cargalleria.model.navigaion

import android.net.Uri
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.example.cargalleria.View.authScrenn.LoginScreen
import com.example.cargalleria.View.authScrenn.SignUpScreen

import com.example.cargalleria.model.Car
import com.example.cargalleria.View.screens.AddCarScreen
import com.example.cargalleria.View.screens.DetailScreen
import com.example.cargalleria.View.screens.FavoritesScreen
import com.example.cargalleria.View.screens.HomeScreen
import com.example.cargalleria.View.screens.ProfileScreen
import com.example.cargalleria.View.screens.Screens
import com.example.cargalleria.View.screens.SettingsScreen
import com.example.cargalleria.View.theme.CarGalleriaTheme
import com.example.cargalleria.viewModel.CarViewModel
import com.google.gson.Gson

@Composable
fun AppNavigations() {
    val navController: NavHostController = rememberNavController()
    val carViewModel: CarViewModel = viewModel()
    var isDarkTheme by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    CarGalleriaTheme(
        darkTheme = isDarkTheme,
        content = {
    Scaffold(
        bottomBar = {
            if (currentDestination != "logIn_screen" && currentDestination != "signIn_screen") {
                NavigationBar(
                ) {
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
                                Text(
                                    text = stringResource(id = navItem.labelRes),
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->

                NavHost(
                    navController = navController,
                    startDestination = Screens.HomeScreen.name,
                    modifier = Modifier.padding(paddingValues)
                ) {
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
                        DetailScreen(car, navController, carViewModel)
                    }
                    composable(route = Screens.FavoritesScreen.name) {
                        FavoritesScreen(carViewModel, navigateToDetail = { car ->
                            val carJson = Uri.encode(Gson().toJson(car))
                            navController.navigate("${Screens.DetailScreen.name}/$carJson")
                        })
                    }
                    composable(route = Screens.SettingScreen.name) {
                        SettingsScreen(
                            darkThemeEnabled = isDarkTheme,
                            toggleDarkTheme = { darkThemeEnabled ->
                                isDarkTheme = darkThemeEnabled
                            },
                            onLanguageClick = {
                            },
                            onLogoutClick = {
                                navController.navigate("logIn_screen") {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                }
                            },
                            navController = navController
                        )
                    }
                    composable(route = "profile_screen") {
                        ProfileScreen(navController)
                    }


                }
            }

    }
    ) }


