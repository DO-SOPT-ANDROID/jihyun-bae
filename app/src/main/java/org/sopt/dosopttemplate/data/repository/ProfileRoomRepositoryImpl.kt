package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.local.ProfileRoomDataSource
import org.sopt.dosopttemplate.data.model.local.ProfileEntity
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.domain.repository.ProfileRoomRepository
import javax.inject.Inject

class ProfileRoomRepositoryImpl @Inject constructor(
    private val profileRoomDataSource: ProfileRoomDataSource
) : ProfileRoomRepository {
    override suspend fun getProfileList(): Result<List<Profile>> = runCatching {
        profileRoomDataSource.getProfileList().map { profileEntity ->
            when (profileEntity.profileType) {
                ProfileEntity.ProfileType.MY_PROFILE -> profileEntity.toMyProfile()
                ProfileEntity.ProfileType.FRIEND_PROFILE -> profileEntity.toFriendProfile()
                ProfileEntity.ProfileType.FRIEND_PROFILE_WITH_MUSIC -> profileEntity.toFriendProfileWithMusic()
                ProfileEntity.ProfileType.FRIEND_PROFILE_WITH_BIRTH -> profileEntity.toFriendProfileWithBirth()
            }
        }
    }

    override suspend fun getProfile(id: Int): Profile {
        val profileEntity = profileRoomDataSource.getProfile(id)

        return when (profileEntity.profileType) {
            ProfileEntity.ProfileType.MY_PROFILE -> profileEntity.toMyProfile()
            ProfileEntity.ProfileType.FRIEND_PROFILE -> profileEntity.toFriendProfile()
            ProfileEntity.ProfileType.FRIEND_PROFILE_WITH_MUSIC -> profileEntity.toFriendProfileWithMusic()
            ProfileEntity.ProfileType.FRIEND_PROFILE_WITH_BIRTH -> profileEntity.toFriendProfileWithBirth()
        }
    }

    override suspend fun insertProfile(profile: Profile) {
        profileRoomDataSource.insertProfile(
            when (profile) {
                is Profile.MyProfile -> profile.toProfileEntity()
                is Profile.FriendProfile -> profile.toProfileEntity()
                is Profile.FriendProfileWithMusic -> profile.toProfileEntity()
                is Profile.FriendProfileWithBirth -> profile.toProfileEntity()
            }
        )
    }

    override suspend fun deleteProfile(profile: Profile) {
        profileRoomDataSource.deleteProfile(
            when (profile) {
                is Profile.MyProfile -> profile.toProfileEntity()
                is Profile.FriendProfile -> profile.toProfileEntity()
                is Profile.FriendProfileWithMusic -> profile.toProfileEntity()
                is Profile.FriendProfileWithBirth -> profile.toProfileEntity()
            }
        )
    }
}