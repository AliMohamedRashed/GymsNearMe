package com.ali.gymsnearme.gyms.model

data class Gym(

    val id: Int,
    val place: String,
    val name: String,
    val isOpen: Boolean,
    val isFavourite: Boolean = false
){
    companion object {
        val empty = Gym(
            id = 0,
            place = "",
            name = "",
            isOpen = false
        )
    }
}