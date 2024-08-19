package com.ali.gymsnearme.gyms.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "gyms"
)
data class LocalGym(
    @ColumnInfo(name = "gym_id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "gym_location")
    val place: String,
    @ColumnInfo(name = "gym_name")
    val name: String,
    val isOpen: Boolean,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean = false
)