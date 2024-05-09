package com.example.cargalleria.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cargalleria.data.Car

class CarViewModel : ViewModel() {
    private val _cars = MutableLiveData<List<Car>>(emptyList())
    val cars: LiveData<List<Car>> = _cars

    fun addCar(newCar: Car) {
        _cars.value = _cars.value?.plus(newCar)
    }

}
