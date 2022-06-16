package com.example.todoapp1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import androidx.room.Query

// database objem.
@Dao
interface UserDao {

    @Query(value = "SELECT * FROM users Order by id ASC")
    fun readAllData() : LiveData<List<User>>

    // user yoksa ekleme.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    //Uzun süren bir işlemi yürütüp, thread bloklaması olmadan tamamlanmasını sağlar.
    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


}