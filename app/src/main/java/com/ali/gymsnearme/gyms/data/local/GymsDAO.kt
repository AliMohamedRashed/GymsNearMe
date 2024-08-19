package com.ali.gymsnearme.gyms.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GymsDAO {

    @Query("SELECT * FROM gyms")
    suspend fun getAll(): List<LocalGym>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(gyms: List<LocalGym>)

    @Update(entity = LocalGym::class)
    suspend fun update(gymFavouriteState: LocalGymFavouriteState)

    @Query("SELECT * FROM gyms WHERE is_favourite = 1")
    suspend fun getFavouriteGyms(): List<LocalGym>

    @Update(entity = LocalGym::class)
    suspend fun updateAll(localGymsState: List<LocalGymFavouriteState>)

    @Query("SELECT * FROM gyms WHERE gym_id = :id")
    suspend fun getGym(id: Int): LocalGym

}