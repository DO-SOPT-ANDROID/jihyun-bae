package org.sopt.dosopttemplate.domain.model

import org.sopt.dosopttemplate.data.model.local.ProfileEntity

sealed class Profile {
    abstract val id: Int

    data class MyProfile(
        override val id: Int,
        val profileImage: String,
        val name: String,
        val description: String?,
    ) : Profile() {
        fun toProfileEntity() = ProfileEntity(
            id = this.id,
            profileType = ProfileEntity.ProfileType.MY_PROFILE,
            profileImage = this.profileImage,
            name = this.name,
            description = this.description,
        )

        fun toParcelizeMyProfile() = org.sopt.dosopttemplate.presentation.model.Profile.MyProfile(
            id = this.id,
            profileImage = this.profileImage,
            name = this.name,
            description = this.description
        )
    }

    data class FriendProfile(
        override val id: Int,
        val profileImage: String,
        val name: String,
        val description: String?
    ) : Profile() {
        fun toProfileEntity() = ProfileEntity(
            id = this.id,
            profileType = ProfileEntity.ProfileType.FRIEND_PROFILE,
            profileImage = this.profileImage,
            name = this.name,
            description = this.description,
        )

        fun toParcelizeFriendProfile() =
            org.sopt.dosopttemplate.presentation.model.Profile.FriendProfile(
                id = this.id,
                profileImage = this.profileImage,
                name = this.name,
                description = this.description
            )
    }

    data class FriendProfileWithMusic(
        override val id: Int,
        val profileImage: String,
        val name: String,
        val description: String?,
        val musicTitle: String,
        val singer: String
    ) : Profile() {
        fun toProfileEntity() = ProfileEntity(
            id = this.id,
            profileType = ProfileEntity.ProfileType.FRIEND_PROFILE_WITH_MUSIC,
            profileImage = this.profileImage,
            name = this.name,
            description = this.description,
            musicTitle = this.musicTitle,
            singer = this.singer
        )

        fun toParcelizeFriendProfileWithMusic() =
            org.sopt.dosopttemplate.presentation.model.Profile.FriendProfileWithMusic(
                id = this.id,
                profileImage = this.profileImage,
                name = this.name,
                description = this.description,
                musicTitle = this.musicTitle,
                singer = this.singer
            )
    }

    data class FriendProfileWithBirth(
        override val id: Int,
        val profileImage: String,
        val name: String,
        val birthMonth: Int,
        val birthDay: Int
    ) : Profile() {
        fun toProfileEntity() = ProfileEntity(
            id = this.id,
            profileType = ProfileEntity.ProfileType.FRIEND_PROFILE_WITH_BIRTH,
            profileImage = this.profileImage,
            name = this.name,
            birthMonth = this.birthMonth,
            birthDay = this.birthDay
        )

        fun toParcelizeFriendProfileWithBirth() =
            org.sopt.dosopttemplate.presentation.model.Profile.FriendProfileWithBirth(
                id = this.id,
                profileImage = this.profileImage,
                name = this.name,
                birthMonth = this.birthMonth,
                birthDay = this.birthDay
            )
    }
}
