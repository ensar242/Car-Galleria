package com.example.cargalleria.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(
    val name: String = "",
    val imageUri: String = "",
    val year: Int = 0,
    val hp: Int = 0,
    val km: Int = 0,
    val price: Int = 0,
    val userid: String = ""
) : Parcelable
