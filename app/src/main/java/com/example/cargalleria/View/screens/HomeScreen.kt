package com.example.cargalleria.View.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.cargalleria.model.Car
import com.example.cargalleria.View.composes.AppTopBar
import com.example.cargalleria.View.composes.CarCard
import com.example.cargalleria.viewModel.CarViewModel

@Composable
fun HomeScreen(carViewModel: CarViewModel, navigateToDetail: (Car) -> Unit) {
    val cars by carViewModel.cars.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        carViewModel.fetchCars()
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "CarGalleria",
                showLogo = true
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                LazyColumn {
                    items(cars) { car ->
                        CarCard(car) {
                            navigateToDetail(car)
                        }
                    }
                }
            }
        }
    )
}
