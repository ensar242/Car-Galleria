package com.example.cargalleria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cargalleria.navigaion.AppNavigations
import com.example.cargalleria.navigaion.Screens
import com.example.cargalleria.ui.theme.screens.HomeScreen
import com.example.cargalleria.ui.theme.screens.authScrenn.SignInScreen
import com.example.cargalleria.ui.theme.screens.authScrenn.loginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController= rememberNavController()
            NavHost(navController = navController, startDestination = "logIn_screen"
            ) {
                composable("signIn_screen"){
                SignInScreen(navController)
            }
                composable("logIn_screen"){
                    loginScreen(navController)
                }

                composable(Screens.HomeScreen.name){
                    AppNavigations()
                }


        }
        }
    }
}


