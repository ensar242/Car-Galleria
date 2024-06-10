package com.example.cargalleria.View.authScrenn

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.sharp.ArrowBackIos
import androidx.compose.material.icons.automirrored.sharp.KeyboardBackspace
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.ArrowBackIos
import androidx.compose.material.icons.sharp.ArrowBackIosNew
import androidx.compose.material.icons.twotone.ArrowBack

import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
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

@SuppressLint("CommitPrefEdits")
@Composable
fun SignUpScreen(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context= LocalContext.current
    val sharedPreferences:SharedPreferences=context.getSharedPreferences("MyPreferences",Context.MODE_PRIVATE)

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
        IconButton(
            onClick = { navController.navigate("logIn_screen") },
            modifier = Modifier
                .size(48.dp)
                .background(MaterialTheme.colorScheme.surface, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Sharp.ArrowBackIosNew,
                contentDescription = "Geri Dön",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
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
                            val editor = sharedPreferences.edit()
                            editor.putString("key_name", name)
                            editor.putString("key_surname", surname)
                            editor.putString("key_email",email)
                            editor.apply()


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
            Text(text = "Sign Up")
        }
    }
}

}
