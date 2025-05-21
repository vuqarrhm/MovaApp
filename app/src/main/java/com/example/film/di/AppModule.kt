package com.example.film.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.film.api.FilmServices
import com.example.film.room.AppDatabase
import com.example.film.room.BasketDAO
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Singleton
    @Provides
    fun provideServices(): FilmServices {
        val retrofit= Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").
        addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(FilmServices::class.java)
    }


    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"appDB").
        fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun providesBasketDao(appDatabase: AppDatabase): BasketDAO {
        return appDatabase.basketDao()
    }

    @Provides
    @Singleton
    fun provideFireBase(): FirebaseAuth {
        return Firebase.auth
    }


    @Singleton
    @Provides
    fun providesSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("localSP",Context.MODE_PRIVATE)
    }




}