package com.example.cargalleria.View.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cargalleria.R
import com.example.cargalleria.View.composes.AppTopBar
import com.example.cargalleria.View.composes.LanguageSelectionDialog
import java.util.Locale

@Composable
fun SettingsScreen(
    navController: NavController,
    darkThemeEnabled: Boolean,
    toggleDarkTheme: (Boolean) -> Unit,
    onLanguageClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    var selectedLanguage by rememberSaveable { mutableStateOf(sharedPreferences.getString("My_Lang", "en") ?: "en") }
    var showDialog by rememberSaveable { mutableStateOf(false) }

    if (showDialog) {
        LanguageSelectionDialog(
            currentLanguage = selectedLanguage,
            onLanguageSelected = { newLanguage ->
                selectedLanguage = newLanguage
                updateLocale(context, newLanguage)
                showDialog = false
            },
            onDismissRequest = { showDialog = false }
        )
    }

    Scaffold(
        topBar = {
            AppTopBar(title = stringResource(id = R.string.Settings), showLogo = true)
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
                SettingItem(
                    icon = Icons.Default.DarkMode,
                    title = stringResource(id = R.string.Theme),
                    description = stringResource(id = R.string.ThemeDesc),
                    trailing = {
                        Switch(
                            checked = darkThemeEnabled,
                            onCheckedChange = { toggleDarkTheme(it) }
                        )
                    }
                )
                SettingItem(
                    icon = Icons.Default.AccountCircle,
                    title = stringResource(id = R.string.Profile),
                    description = stringResource(id = R.string.ProfileDesc),
                    onClick = { navController.navigate("profile_screen") }
                )

                SettingItem(
                    icon = Icons.Default.Language,
                    title = stringResource(id = R.string.Language),
                    description = stringResource(id = R.string.LanguageDesc),
                    onClick = { showDialog = true }
                )

                SettingItem(
                    icon = Icons.Default.Logout,
                    title = stringResource(id = R.string.Logout),
                    description = stringResource(id = R.string.LogoutDesc),
                    onClick = {
                        onLogoutClick()
                        navController.navigate("logIn_screen") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    )
}

@Composable
fun SettingItem(
    icon: ImageVector,
    title: String,
    description: String,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    descriptionColor: Color = MaterialTheme.colorScheme.onBackground,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick?.invoke() })
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = titleColor)
            Text(text = description, fontSize = 14.sp, color = descriptionColor)
        }
        trailing?.invoke()
    }
}
fun updateLocale(context: Context, language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val resources = context.resources
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)

    val editor: SharedPreferences.Editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
    editor.putString("My_Lang", language)
    editor.apply()

    val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    (context as? AppCompatActivity)?.finish()
}