package com.example.cargalleria.data

import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.api.Context
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import java.util.UUID
fun uploadImageToFirebaseStorage(imageUri: Uri, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
    val storageRef = Firebase.storage.reference
    val imagesRef = storageRef.child("images/${UUID.randomUUID()}.jpg")

    imagesRef.putFile(imageUri)
        .addOnSuccessListener {
            imagesRef.downloadUrl.addOnSuccessListener { uri ->
                onSuccess(uri.toString())
            }
        }
        .addOnFailureListener { exception ->
            onFailure(exception)
        }
}
