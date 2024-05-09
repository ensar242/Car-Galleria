package com.example.cargalleria.navigaion

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label:String,
    val icon:ImageVector,
    val route:String

)

val listOfNavItems:List<NavItem> = listOf(
      NavItem(
        "Home",
        Icons.Default.Home,
        Screens.HomeScreen.name

    ),
      NavItem("Add",
    Icons.Default.Add,
    Screens.AddCarScreen.name)   ,
     NavItem(
"Favorites",
Icons.Default.FavoriteBorder,
Screens.FavoritesScreen.name

)   ,
        NavItem(
"Settings",
Icons.Default.Settings,
Screens.SettingScreen.name

)
)