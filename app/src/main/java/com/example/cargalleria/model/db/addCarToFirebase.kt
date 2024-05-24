package com.example.cargalleria.model.db

import androidx.compose.runtime.internal.illegalDecoyCallException
import com.example.cargalleria.model.Car
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore



fun addCarToFirebase(car: Car) {
    val db = Firebase.firestore

    // Create a document reference with car's ID
    val carDocRef = db.collection("cars").document(car.id)

    // Convert car object to a map
    val carData = hashMapOf(
        "id" to car.id,
        "name" to car.name,
        "imageUri" to car.imageUri,
        "year" to car.year,
        "hp" to car.hp,
        "km" to car.km,
        "price" to car.price,
        "userId" to car.userid
    )

    // Set the data to the document reference
    carDocRef
        .set(carData)
        .addOnSuccessListener {
            println("Car added with ID: ${car.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding car: $e")
        }
}
