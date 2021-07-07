package com.joco.roomp2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java as java

@Database(entities = [User::class],version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao():UserDao
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).allowMainThreadQueries()
                        .build()
                        INSTANCE = instance
                    return instance

                }

        }
    }
}