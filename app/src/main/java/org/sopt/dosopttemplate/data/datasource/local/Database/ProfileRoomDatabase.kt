package org.sopt.dosopttemplate.data.datasource.local.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.sopt.dosopttemplate.data.datasource.local.Dao.ProfileDao
import org.sopt.dosopttemplate.data.model.local.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1)
abstract class ProfileRoomDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}