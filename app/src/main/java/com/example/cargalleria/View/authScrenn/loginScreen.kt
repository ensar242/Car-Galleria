package com.example.cargalleria.View.authScrenn

import androidx.compose.foundation.Image

import androidx.compose.ui.res.painterResource

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cargalleria.R
import com.example.cargalleria.View.screens.Screens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun loginScreen(navController: NavController) {
    val firebaseAuth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var snackbarHostState = remember { SnackbarHostState() }
    var coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "login screen image",
                modifier = Modifier.size(350.dp)
            )
            Spacer(modifier = Modifier.padding(0.dp))
            Text(text = "Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Login to your account")
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email")
                }, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )

            )

            Spacer(modifier = Modifier.height(12.dp))
            FilledTonalButton(
                onClick = {

                    if (email.isNotEmpty()  &&  password.isNotEmpty()){

                        firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    navController.navigate(Screens.HomeScreen.name)
                                } else {
                                    coroutineScope.launch {
                                        val error = task.exception?.localizedMessage ?: "Sign-in failed"
                                        snackbarHostState.showSnackbar("Wrong email or password")
                                    }
                                }
                            }

                    }else{
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please enter email and password")
                        }}}
            ) {
                Text(text = "Login")
            }

            TextButton(onClick = { navController.navigate("signIn_Screen") }) {
                Text("Don't have an account? Sign Up Now.", color = Color.Black)
            }
        }
    }
}
