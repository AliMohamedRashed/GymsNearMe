package com.ali.gymsnearme.gyms.data

import com.ali.gymsnearme.GymsApplication
import com.ali.gymsnearme.gyms.data.local.GymsDataBase
import com.ali.gymsnearme.gyms.model.Gym
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GymsDetailsRepository {

    private val gymsDao = GymsDataBase.getDAOInstance(GymsApplication.getApplicationContext())

    suspend fun getGymsFromRemoteDB(id: Int)  = withContext(Dispatchers.IO){
        gymsDao.getGym(id).let {
            Gym(
                id = it.id,
                place = it.name,
                name = it.place,
                isOpen = it.isOpen,
            )
        }
    }
}