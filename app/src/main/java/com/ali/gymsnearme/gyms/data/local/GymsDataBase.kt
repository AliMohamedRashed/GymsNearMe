package com.ali.gymsnearme.gyms.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LocalGym::class],
    version = 4,
    exportSchema = false
)
abstract class GymsDataBase: RoomDatabase() {
    abstract val dao: GymsDAO
    companion object{
        @Volatile
        private var daoInstance: GymsDAO? = null

        private fun buildDatabase(context: Context): GymsDataBase =
            Room.databaseBuilder(
                context,
                GymsDataBase::class.java,
                "gyms_database"
            ).fallbackToDestructiveMigration().build()

        fun getDAOInstance(context: Context): GymsDAO {
            synchronized(this){
                if(daoInstance == null){
                    daoInstance = buildDatabase(context).dao
                }
                return daoInstance as GymsDAO
            }
        }

    }
}