package com.example.cargalleria.View

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cargalleria.View.authScrenn.LoginScreen
import com.example.cargalleria.model.navigaion.AppNavigations
import com.example.cargalleria.View.authScrenn.SignUpScreen
import com.example.cargalleria.View.screens.Screens

import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination ="logIn_screen") {
                composable(Screens.HomeScreen.name) {
                    AppNavigations()
                }
                composable("signIn_screen") {

                    SignUpScreen(navController)
                }
                composable("logIn_screen") {
                    LoginScreen(navController)
                }

            }
        }

        window.decorView.apply {
            WindowInsetsControllerCompat(window, this).let { controller ->
                controller.hide(WindowInsetsCompat.Type.statusBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }

    private fun loadLocale() {
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "en")
        setLocale(language ?: "en")
    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }
}

