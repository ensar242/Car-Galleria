package com.example.cargalleria.ui.theme.screens.authScrenn

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack

import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cargalleria.R

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(navController: NavController) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context= LocalContext.current
    var name by remember {
        mutableStateOf("")
    }
    var surname by remember {
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->

        OutlinedIconButton(onClick = { navController.navigate("logIn_screen")})
        {
            Icon(Icons.TwoTone.ArrowBack, "Floating action button.")

        }
    Column(
        modifier = Modifier.fillMaxSize()
        , verticalArrangement = Arrangement.Top
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {



            Image(
            painter = painterResource(id = R.drawable.imageforsign),
            contentDescription = "sign in  screen image",
            modifier = Modifier.size(280.dp)
        )
        Spacer(modifier = Modifier.padding(0.dp))
        Text(text = "Hey There", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Create ann Account")
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = name, onValueChange = {
                                                        name=it
        }, label = {
            Text(text = "Name")
        }, keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ))
        Spacer(modifier = Modifier.height(2.dp))
        OutlinedTextField(value = surname, onValueChange = {
                                                           surname=it
        }, label = {
            Text(text = "Surname")
        }, keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ))
        OutlinedTextField(value = email, onValueChange = {
                                                         email=it
        }, label = {
            Text(text = "Email address")
        } , keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ))
        Spacer(modifier = Modifier.height(2.dp))
        OutlinedTextField(value = password, onValueChange = {
                                                            password=it
        }, label = {
            Text(text = "Password")
        }, visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ))
        Spacer(modifier = Modifier.height(12.dp))
        FilledTonalButton(onClick = {
            if(name.isNotEmpty() && surname.isNotEmpty()&& email.isNotEmpty() && password.isNotEmpty()){


                val firebaseAuth = FirebaseAuth.getInstance()

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->


                        if (task.isSuccessful) {

                            Toast.makeText(context,"Login Please", Toast.LENGTH_LONG).show()
                            navController.navigate("logIn_screen")


                        } else {
                            val error = task.exception?.localizedMessage ?: "Sign-up failed"
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Please enter a valid address")
                            }
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)


                        }
                    }
            }
            else{

                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Please enter all values")
                }


            }



        }) {

            Text(text = "Sign In")

        }

    }






}

}
