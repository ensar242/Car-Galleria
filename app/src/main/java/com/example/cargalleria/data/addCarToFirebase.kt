package com.example.cargalleria.data

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore



fun addCarToFirebase(car: Car) {
    val db = Firebase.firestore
    val carData = hashMapOf(
        "name" to car.name,
        "imageUri" to car.imageUri,
        "year" to car.year,
        "hp" to car.hp,
        "km" to car.km,
        "price" to car.price,
        "userId" to car.userid
    )

    db.collection("cars")
        .add(carData)
        .addOnSuccessListener { documentReference ->
            println("Car added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding car: $e")
        }
}