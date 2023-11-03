package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.Profile

interface ProfileRoomRepository {
    suspend fun getProfileList(): Result<List<Profile>>
    suspend fun getProfile(id: Int): Profile
    suspend fun insertProfile(profile: Profile)
    suspend fun deleteProfile(profile: Profile)
}