package com.example.cargalleria.View.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cargalleria.R
import com.example.cargalleria.model.Car
import com.example.cargalleria.View.composes.AppTopBar
import com.example.cargalleria.View.composes.DeleteConfirmationDialog
import com.example.cargalleria.View.composes.DetailItem
import com.example.cargalleria.viewModel.CarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(car: Car, navController: NavHostController, carViewModel: CarViewModel) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(car.name, color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.TwoTone.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onBackground)
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    AsyncImage(
                        model = car.imageUri,
                        contentDescription = car.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .background(Color.Black.copy(alpha = 0.7f))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = car.name,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                DetailItem(icon = Icons.Default.CalendarToday, label = stringResource(id = R.string.Year), value = car.year.toString())
                DetailItem(icon = Icons.Default.Speed, label = stringResource(id = R.string.Hp), value = car.hp.toString())
                DetailItem(icon = Icons.Default.DirectionsCar, label = stringResource(id = R.string.Km), value = car.km.toString())
                DetailItem(icon = Icons.Default.AttachMoney, label = stringResource(id = R.string.Price), value = "${car.price}€")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { setShowDialog(true) },
                 containerColor = MaterialTheme.colorScheme.inversePrimary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
            if (showDialog) {
                DeleteConfirmationDialog(
                    onConfirm = {
                        carViewModel.deleteCar(car)
                        navController.popBackStack()
                        setShowDialog(false)
                    },
                    onDismiss = { setShowDialog(false) }
                )
            }
        }
    )
}