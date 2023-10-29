package org.sopt.dosopttemplate.domain.model

import androidx.annotation.DrawableRes

sealed class Profile {
    data class MyProfile(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?,
    ) : Profile() {
        fun toMyProfile() = org.sopt.dosopttemplate.presentation.model.Profile.MyProfile(
            profileImage = this.profileImage,
            name = this.name,
            description = this.description
        )
    }

    data class FriendProfile(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?
    ) : Profile() {
        fun toFriendProfile() = org.sopt.dosopttemplate.presentation.model.Profile.FriendProfile(
            profileImage = this.profileImage,
            name = this.name,
            description = this.description
        )
    }

    data class FriendProfileWithMusic(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?,
        val musicTitle: String,
        val singer: String
    ) : Profile() {
        fun toFriendProfileWithMusic() =
            org.sopt.dosopttemplate.presentation.model.Profile.FriendProfileWithMusic(
                profileImage = this.profileImage,
                name = this.name,
                description = this.description,
                musicTitle = this.musicTitle,
                singer = this.singer
            )
    }

    data class FriendProfileWithBirth(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val birthMonth: Int,
        val birthDay: Int
    ) : Profile() {
        fun toFriendProfileWithBirth() =
            org.sopt.dosopttemplate.presentation.model.Profile.FriendProfileWithBirth(
                profileImage = this.profileImage,
                name = this.name,
                birthMonth = this.birthMonth,
                birthDay = this.birthDay
            )
    }
}
