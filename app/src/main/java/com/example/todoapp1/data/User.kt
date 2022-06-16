package com.example.todoapp1.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//Bunun bir tablo olduğunu ve içindekilerden oluştuğunu biliyor.
// delete update için bir primary key ihtiyaç vardır.

@Parcelize
@Entity (tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val not:String,
    val aciklama : String) : Parcelable
