package com.example.cargalleria.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cargalleria.model.Car
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class CarViewModel : ViewModel() {
    private val _cars = MutableLiveData<List<Car>>(emptyList())
    private val _allCars = mutableListOf<Car>() // Tüm arabaları saklamak için
    private val _favoriteCars = MutableLiveData<List<Car>>(emptyList()) // Favori arabaları saklamak için

    private val db = Firebase.firestore
    private val carsCollection = db.collection("cars")

    val cars: LiveData<List<Car>> = _cars
    val favoriteCars: LiveData<List<Car>> = _favoriteCars

    fun addCar(newCar: Car) {
        _allCars.add(newCar)
        _cars.value = _allCars
    }

    fun fetchCars() {
        carsCollection.get()
            .addOnSuccessListener { result ->
                val carList = mutableListOf<Car>()
                for (document in result) {
                    val car = document.toObject<Car>()
                    carList.add(car)
                }
                _allCars.clear()
                _allCars.addAll(carList)
                _cars.value = carList
            }
            .addOnFailureListener { exception ->
                Log.w("CarViewModel", "Error getting documents: ", exception)
            }
    }

    fun searchCars(query: String) {
        val filteredCars = if (query.isEmpty()) {
            _allCars
        } else {
            val lowerCaseQuery = query.lowercase()
            _allCars.filter {
                it.name.lowercase().contains(lowerCaseQuery)
            }
        }
        _cars.value = filteredCars
    }

    fun toggleFavorite(car: Car) {
        val currentFavorites = _favoriteCars.value ?: emptyList()
        if (currentFavorites.contains(car)) {
            _favoriteCars.value = currentFavorites.filter { it.id != car.id }
        } else {
            _favoriteCars.value = currentFavorites + car
        }
    }

    fun deleteCar(car: Car) {
        carsCollection.document(car.id)
            .delete()
            .addOnSuccessListener {
                Log.d("CarViewModel", "Car deleted successfully from Firestore")
                _allCars.remove(car)
                _cars.value = _allCars.filter { it.id != car.id }
                _favoriteCars.value = _favoriteCars.value?.filter { it.id != car.id }
            }
            .addOnFailureListener { exception ->
                Log.w("CarViewModel", "Error deleting car from Firestore", exception)
            }
    }

}
