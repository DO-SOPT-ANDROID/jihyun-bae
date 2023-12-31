package org.sopt.dosopttemplate.data.datasource.local.DataSourceImpl

import org.sopt.dosopttemplate.data.datasource.local.DataSource.ProfileRoomDataSource
import org.sopt.dosopttemplate.data.datasource.local.Database.ProfileRoomDatabase
import org.sopt.dosopttemplate.data.model.local.ProfileEntity
import javax.inject.Inject

class ProfileRoomDataSourceImpl @Inject constructor(
    private val profileRoomDatabase: ProfileRoomDatabase
) : ProfileRoomDataSource {
    override suspend fun getProfileList() = profileRoomDatabase.profileDao().getProfileList()

    override suspend fun getProfile(id: Int) = profileRoomDatabase.profileDao().getProfile(id)
    override suspend fun insertProfile(profile: ProfileEntity) {
        profileRoomDatabase.profileDao().insertProfile(profile)
    }

    override suspend fun deleteProfile(profile: ProfileEntity) {
        profileRoomDatabase.profileDao().deleteProfile(profile)
    }
}