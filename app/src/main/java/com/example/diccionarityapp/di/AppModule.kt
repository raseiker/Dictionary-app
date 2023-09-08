package com.example.diccionarityapp.di

import android.app.Application
import androidx.room.Room
import com.example.diccionarityapp.data.database.DictionaryDatabase
import com.example.diccionarityapp.data.repo.DictionaryRepository
import com.example.diccionarityapp.data.repo.DictionaryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //provide database
    @Provides
    @Singleton
    fun provideDatabase(app: Application): DictionaryDatabase {
        return Room.databaseBuilder(
            context = app.baseContext,
            klass = DictionaryDatabase::class.java,
            name = "Dictionary"
        ).build()
    }

    //provide repository
    @Provides
    @Singleton
    fun provideDictionaryRepository(db: DictionaryDatabase): DictionaryRepository {
        return DictionaryRepositoryImpl(db.getDao())
    }


}