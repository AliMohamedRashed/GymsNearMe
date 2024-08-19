package com.ali.gymsnearme.gyms.model

import com.ali.gymsnearme.gyms.data.GymsRepository
import javax.inject.Inject

class GetSortedGymsUseCase @Inject constructor(
    private var gymsRepository: GymsRepository
) {

    suspend operator fun invoke(): List<Gym>{
        return gymsRepository.getGyms().sortedBy { it.name }
    }
}