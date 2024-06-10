package com.example.cargalleria.View.screens

import CarCard
import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.cargalleria.R
import com.example.cargalleria.View.composes.AppTopBar
import com.example.cargalleria.View.theme.CarGalleriaTheme
import com.example.cargalleria.model.Car
import com.example.cargalleria.viewModel.CarViewModel

@Composable
fun FavoritesScreen(carViewModel: CarViewModel, navigateToDetail: (Car) -> Unit) {
    val favoriteCars by carViewModel.favoriteCars.observeAsState(emptyList())
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.Favorite),
                showLogo = true
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                if (favoriteCars.isEmpty()) {
                    Text(text = stringResource(id = R.string.FavoritesDesc), fontSize = 22.sp, color = MaterialTheme.colorScheme.onBackground)
                } else {
                    LazyColumn {
                        items(favoriteCars) { car ->
                            CarCard(
                                car = car,
                                isFavorite = true,
                                onFavoriteClick = { carViewModel.toggleFavorite(it) },
                                onClick = { navigateToDetail(car) }
                            )
                        }
                    }
                }
            }
        }
    )
}
