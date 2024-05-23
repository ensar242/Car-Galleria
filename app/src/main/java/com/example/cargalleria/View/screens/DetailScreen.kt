package com.example.cargalleria.View.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cargalleria.model.Car
import com.example.cargalleria.View.composes.AppTopBar

@Composable
fun DetailScreen(car: Car, navController: NavHostController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = car.name,
                showLogo = false,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() } ) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AsyncImage(
                    model = car.imageUri,
                    contentDescription = car.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
                Text(text = car.name, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
                Text(text = "Year: ${car.year}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
                Text(text = "HP: ${car.hp}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
                Text(text = "KM: ${car.km}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
                Text(text = "Price: ${car.price}€", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(8.dp))
            }
        }
    )
}
