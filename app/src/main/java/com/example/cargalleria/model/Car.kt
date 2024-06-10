package com.example.cargalleria.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.UUID
@Entity(tableName = "cars")
data class Car(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var name: String = "",
    var imageUri: String = "",
    var year: Int = 0,
    var hp: Int = 0,
    var km: Int = 0,
    var price: Int = 0,
    var userid: String = ""
)