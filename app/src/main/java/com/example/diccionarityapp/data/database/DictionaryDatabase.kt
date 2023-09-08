package com.example.diccionarityapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class DictionaryDatabase : RoomDatabase() {

    //function for accessing dictionary dao
    abstract fun getDao(): DictionaryDao

    //initializes database in singleton pattern
//    companion object {
//        @Volatile
//        private var INSTANCE: DictionaryDatabase? = null
//
//        fun getDatabase(context: Context): DictionaryDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context = context,
//                    klass = DictionaryDatabase::class.java,
//                    name = "Dictionary"
//                )
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }

}