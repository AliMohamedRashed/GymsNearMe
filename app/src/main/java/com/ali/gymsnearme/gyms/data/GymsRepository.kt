package com.ali.gymsnearme.gyms.data

import com.ali.gymsnearme.GymsApplication
import com.ali.gymsnearme.gyms.data.local.GymsDAO
import com.ali.gymsnearme.gyms.data.local.GymsDataBase
import com.ali.gymsnearme.gyms.data.local.LocalGym
import com.ali.gymsnearme.gyms.data.local.LocalGymFavouriteState
import com.ali.gymsnearme.gyms.data.remote.GymsApiService
import com.ali.gymsnearme.gyms.model.Gym
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GymsRepository @Inject constructor(
    private val apiService: GymsApiService,
    private var gymsDao: GymsDAO
){

    suspend fun loadGyms() = withContext(Dispatchers.IO){
        try {
            updateLocalDatabase()
        }catch (ex:Exception){
            if(  gymsDao.getAll().isEmpty()){
                throw Exception("Something went wrong. No data was found, try reconnecting to the internet")
            }
        }
    }

    suspend fun getGyms(): List<Gym>{
        return withContext(Dispatchers.IO){
            return@withContext gymsDao.getAll().map {
                Gym(it.id,it.name,it.place,it.isOpen,it.isFavourite)
            }
        }
    }

    private suspend fun updateLocalDatabase() {
        val gyms = apiService.getGyms()
        val favouriteGymsList = gymsDao.getFavouriteGyms()
        gymsDao.addAll(gyms.map {
            LocalGym(it.id,it.name,it.place,it.isOpen)
        })
        gymsDao.updateAll(
            favouriteGymsList.map { LocalGymFavouriteState(id = it.id, isFavourite = true) }
        )
    }

    suspend fun toggleFavouriteGym(gymId: Int, state: Boolean) = withContext(Dispatchers.IO){
        gymsDao.update(
            LocalGymFavouriteState(
                id = gymId,
                isFavourite = state
            )
        )
        gymsDao.getAll()
    }
}