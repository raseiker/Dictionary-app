package com.example.diccionarityapp

import android.app.Application
import com.example.diccionarityapp.data.database.DictionaryDatabase
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class that create one instance of the database during the app lifecycle
 */
@HiltAndroidApp
class DictionaryApplication: Application() {
//    val database: DictionaryDatabase by lazy {
//        DictionaryDatabase.getDatabase(this)
//    }
}