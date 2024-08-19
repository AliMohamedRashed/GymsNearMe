package com.ali.gymsnearme.gyms.model

import com.ali.gymsnearme.gyms.data.GymsRepository
import javax.inject.Inject

class GetInitialGymsUseCase @Inject constructor(
    private val gymsRepository: GymsRepository,
    private val getSortedGymsUseCase: GetSortedGymsUseCase
){
    suspend operator fun invoke():List<Gym>{
        gymsRepository.loadGyms()
        return getSortedGymsUseCase()
    }
}