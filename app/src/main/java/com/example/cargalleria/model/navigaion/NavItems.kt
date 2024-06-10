package com.example.cargalleria.model.navigaion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cargalleria.View.screens.Screens

data class NavItem(
    val labelRes: Int,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        com.example.cargalleria.R.string.home,
        Icons.Default.Home,
        Screens.HomeScreen.name
    ),
    NavItem(
        com.example.cargalleria.R.string.add,
        Icons.Default.Add,
        Screens.AddCarScreen.name
    ),
    NavItem(
        com.example.cargalleria.R.string.Favorite,
        Icons.Default.FavoriteBorder,
        Screens.FavoritesScreen.name
    ),
    NavItem(
        com.example.cargalleria.R.string.Settings,
        Icons.Default.Settings,
        Screens.SettingScreen.name
    )
)

@Composable
fun NavItemLabel(navItem: NavItem) {
    Text(text = stringResource(id = navItem.labelRes))
}

@Composable
fun NavigationMenu(navController: NavController) {
    Column {
        listOfNavItems.forEach { navItem ->
            OutlinedButton(
                onClick = { navController.navigate(navItem.route) }
            ) {
                Icon(imageVector = navItem.icon, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                NavItemLabel(navItem = navItem)
            }
        }
    }
}
