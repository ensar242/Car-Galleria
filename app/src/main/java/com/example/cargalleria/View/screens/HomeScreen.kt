package com.example.cargalleria.View.screens

import CarCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.LocalTextStyle
import com.example.cargalleria.R
import com.example.cargalleria.model.Car
import com.example.cargalleria.View.composes.AppTopBar
import com.example.cargalleria.viewModel.CarViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(carViewModel: CarViewModel, navigateToDetail: (Car) -> Unit) {
    val cars by carViewModel.cars.observeAsState(emptyList())
    val favoriteCars by carViewModel.favoriteCars.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        carViewModel.fetchCars()
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = if (isSearching) "" else "CarGalleria",
                showLogo = !isSearching,
                actions = {
                    if (isSearching) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            TextField(
                                value = searchQuery,
                                onValueChange = {
                                    searchQuery = it
                                    carViewModel.searchCars(it)
                                },
                                placeholder = { Text(stringResource(id = R.string.search), color = MaterialTheme.colorScheme.onBackground) },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                textStyle = androidx.compose.material3.LocalTextStyle.current.copy(
                                    color = MaterialTheme.colorScheme.onBackground
                                ),
                                leadingIcon = {
                                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                                },
                                trailingIcon = {
                                    IconButton(onClick = {
                                        isSearching = false
                                        searchQuery = ""
                                        carViewModel.fetchCars()
                                    }) {
                                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                                    }
                                },
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = { isSearching = true }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onBackground)
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                LazyColumn {
                    items(cars) { car ->
                        CarCard(
                            car = car,
                            isFavorite = favoriteCars.contains(car),
                            onFavoriteClick = { carViewModel.toggleFavorite(it) },
                            onClick = { navigateToDetail(car) }
                        )
                    }
                }
            }
        }
    )
}
