package com.ali.gymsnearme.gyms.data.remote

import com.google.gson.annotations.SerializedName


data class RemoteGym(
    val id: Int,
    @SerializedName("gym_location")
    val place: String,
    @SerializedName("gym_name")
    val name: String,
    @SerializedName("is_open")
    val isOpen: Boolean,
)