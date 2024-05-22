package com.example.cargalleria.View.View.composes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cargalleria.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String = "CarGalleria",
    showLogo: Boolean = true,
    navigationIcon: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showLogo) {
                    Image(
                        painter = painterResource(R.mipmap.ic_launcher_foreground), // Uygulama logosu için kaynak
                        contentDescription = "App Logo",
                        modifier = Modifier.size(45.dp) // Logo boyutu
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Logo ile başlık arasına boşluk
                }
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center // Başlığı ortalayın
                )
            }
        }

    )
}
