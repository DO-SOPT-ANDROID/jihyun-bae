package org.sopt.dosopttemplate.data.datasource.local.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.sopt.dosopttemplate.data.model.local.ProfileEntity

@Dao
interface ProfileDao {
    @Query(GET_PROFILE_LIST_QUERY)
    suspend fun getProfileList(): List<ProfileEntity>

    @Query(GET_PROFILE_QUERY)
    suspend fun getProfile(id: Int): ProfileEntity

    @Insert
    suspend fun insertProfile(profile: ProfileEntity)

    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)

    companion object {
        const val GET_PROFILE_LIST_QUERY = "SELECT * FROM profile_table"
        const val GET_PROFILE_QUERY = "SELECT * FROM profile_table WHERE id = :id"
    }
}