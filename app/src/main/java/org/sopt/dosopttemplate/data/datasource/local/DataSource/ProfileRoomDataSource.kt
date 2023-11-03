package org.sopt.dosopttemplate.data.datasource.local.DataSource

import org.sopt.dosopttemplate.data.model.local.ProfileEntity

interface ProfileRoomDataSource {
    suspend fun getProfileList(): List<ProfileEntity>
    suspend fun getProfile(id: Int): ProfileEntity
    suspend fun insertProfile(profile: ProfileEntity)
    suspend fun deleteProfile(profile: ProfileEntity)
}