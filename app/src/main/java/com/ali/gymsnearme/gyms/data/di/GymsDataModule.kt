package com.ali.gymsnearme.gyms.data.di

import android.content.Context
import androidx.room.Room
import com.ali.gymsnearme.gyms.data.local.GymsDAO
import com.ali.gymsnearme.gyms.data.local.GymsDataBase
import com.ali.gymsnearme.gyms.data.remote.GymsApiService
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
object GymsDataModule {

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): GymsApiService{
        return retrofit.create(GymsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(
                "https://gyms-57f4f-default-rtdb.firebaseio.com/"
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    fun provideRoomDao(
        db: GymsDataBase
    ): GymsDAO{
        return db.dao
    }

    @Singleton
    @Provides
    fun provideRoomDataBase(
        @ApplicationContext context: Context
    ): GymsDataBase{
        return Room.databaseBuilder(
            context,
            GymsDataBase::class.java,
            "gyms_database"
        ).fallbackToDestructiveMigration().build()
    }
}