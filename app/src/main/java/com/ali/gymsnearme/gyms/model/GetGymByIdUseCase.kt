package com.ali.gymsnearme.gyms.model

import com.ali.gymsnearme.gyms.data.GymsDetailsRepository

class GetGymByIdUseCase {

    private val gymsDetailsRepository = GymsDetailsRepository()

    suspend operator fun invoke(id:Int): Gym{
        return gymsDetailsRepository.getGymsFromRemoteDB(id)
    }
}