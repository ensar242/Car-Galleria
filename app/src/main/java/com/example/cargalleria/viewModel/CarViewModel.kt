package com.example.cargalleria.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cargalleria.model.Car
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class CarViewModel : ViewModel(){
    private val _cars = MutableLiveData<List<Car>>(emptyList())
    private val db = Firebase.firestore
    private val carsCollection = db.collection("cars")

    val cars: LiveData<List<Car>> = _cars
    fun addCar(newCar: Car) {
        _cars.value = _cars.value?.plus(newCar)
    }
    fun fetchCars() {
        carsCollection.get()
            .addOnSuccessListener { result ->
                val carList = mutableListOf<Car>()
                for (document in result) {
                    val car = document.toObject<Car>()
                    carList.add(car)
                }
                _cars.value = carList
            }
            .addOnFailureListener { exception ->
                Log.w("CarViewModel", "Error getting documents: ", exception)
            }
    }
    fun deleteCar(car: Car) {
        carsCollection.document(car.id)
            .delete()
            .addOnSuccessListener {
                Log.d("CarViewModel", "Car deleted successfully from Firestore")
                // Optionally, you can remove the car locally as well
                _cars.value = _cars.value?.filter { it.id != car.id }
            }
            .addOnFailureListener { exception ->
                Log.w("CarViewModel", "Error deleting car from Firestore", exception)
            }
    }
}