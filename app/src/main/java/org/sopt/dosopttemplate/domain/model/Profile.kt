package org.sopt.dosopttemplate.domain.model

import androidx.annotation.DrawableRes

sealed class Profile {
    data class MyProfile(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?,
    ) : Profile()

    data class FriendProfile(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?
    ) : Profile()

    data class FriendProfileWithMusic(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?,
        val musicTitle: String,
        val singer: String
    ) : Profile()

    data class FriendProfileWithBirth(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val birthMonth: Int,
        val birthDay: Int
    ) : Profile()
}
