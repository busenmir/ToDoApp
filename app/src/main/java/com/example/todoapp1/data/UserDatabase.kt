package com.example.todoapp1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [User::class] , version = 1, exportSchema = false)
abstract  class UserDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao

    companion object
    {
        @Volatile
        private var INSTANCE : UserDatabase? = null
        private val lock = Any()

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context) : UserDatabase
        {
            var tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(lock){
                val instance = Room.databaseBuilder(context,UserDatabase::class.java,"user_database").build()
                tempInstance = instance
                return instance
            }
        }



    }
}