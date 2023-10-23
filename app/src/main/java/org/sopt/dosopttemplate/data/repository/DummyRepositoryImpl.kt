package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.local.DataSource.DummyDataSource
import org.sopt.dosopttemplate.data.model.local.Profile
import org.sopt.dosopttemplate.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyDataSource: DummyDataSource
) : DummyRepository {
    override fun getMockProfileList() = dummyDataSource.mockProfileList.map { profile ->
        when (profile) {
            is Profile.MyProfile -> profile.toMyProfile()
            is Profile.FriendProfile -> profile.toFriendProfile()
            is Profile.FriendProfileWithMusic -> profile.toFriendProfileWithMusic()
            is Profile.FriendProfileWithBirth -> profile.toFriendProfileWithBirth()
        }
    }

}