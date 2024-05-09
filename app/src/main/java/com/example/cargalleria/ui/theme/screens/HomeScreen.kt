package com.example.cargalleria.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cargalleria.R
import com.example.cargalleria.ViewModel.CarViewModel

@Composable
fun HomeScreen(carViewModel: CarViewModel){
    val cars by carViewModel.cars.observeAsState(emptyList()) // ViewModel'dan araba listesini al
    Scaffold(
        topBar = { AppTopBar() }, // Üst kısma TopAppBar eklenir
        content = { paddingValues ->
            // İçerik burada gösterilir
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                LazyColumn {
                    items(cars) { car ->
                        Column {
                            Text(car.name) // Araba adı
                            if (car.imageUri.isNotEmpty()) { // Uri varsa resim göster
                                Image(
                                    painter = rememberAsyncImagePainter(car.imageUri),
                                    contentDescription = car.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.height(200.dp).fillMaxWidth()
                                )
                            }
                        }
                    }
                }

            }
        }
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.mipmap.ic_launcher_foreground), // Uygulama logosu için kaynak
                    contentDescription = "App Logo",
                    modifier = Modifier.size(45.dp) // Logo boyutu
                )
                Spacer(modifier = Modifier.width(8.dp)) // Logo ile başlık arasına boşluk
                Text(
                    text = "CarGalleria",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center // Başlığı ortalayın
                )
            }
        },
        actions = {
            // Eylem düğmeleri veya menüler buraya eklenebilir
        }
    )
}
