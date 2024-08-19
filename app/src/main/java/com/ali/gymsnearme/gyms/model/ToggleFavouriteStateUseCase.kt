package com.ali.gymsnearme.gyms.model

import com.ali.gymsnearme.gyms.data.GymsRepository
import javax.inject.Inject

class ToggleFavouriteStateUseCase @Inject constructor(
    private val gymsRepository: GymsRepository,
    private var getSortedGymsUseCase: GetSortedGymsUseCase
){

    suspend operator fun invoke(id:Int, oldValue:Boolean):List<Gym>{
        val newState = oldValue.not()
        gymsRepository.toggleFavouriteGym(id,newState)
        return getSortedGymsUseCase()
    }
}