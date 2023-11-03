package org.sopt.dosopttemplate.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Profile : Parcelable {
    abstract val id: Int

    @Parcelize
    data class MyProfile(
        override val id: Int,
        val profileImage: String,
        val name: String,
        val description: String?
    ) : Profile(), Parcelable

    @Parcelize
    data class FriendProfile(
        override val id: Int,
        val profileImage: String,
        val name: String,
        val description: String?
    ) : Profile(), Parcelable

    @Parcelize
    data class FriendProfileWithMusic(
        override val id: Int,
        val profileImage: String,
        val name: String,
        val description: String?,
        val musicTitle: String,
        val singer: String
    ) : Profile(), Parcelable

    @Parcelize
    data class FriendProfileWithBirth(
        override val id: Int,
        val profileImage: String,
        val name: String,
        val birthMonth: Int,
        val birthDay: Int
    ) : Profile(), Parcelable
}
