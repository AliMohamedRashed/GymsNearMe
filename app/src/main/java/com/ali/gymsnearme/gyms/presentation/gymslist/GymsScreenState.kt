package com.ali.gymsnearme.gyms.presentation.gymslist

import com.ali.gymsnearme.gyms.model.Gym

data class GymsScreenState (
    val gyms: List<Gym>,
    val isLoading: Boolean,
    val error: String? = null
)