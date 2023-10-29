package org.sopt.dosopttemplate.presentation.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Profile : Parcelable {
    @Parcelize
    data class MyProfile(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?,
    ) : Profile(), Parcelable

    @Parcelize
    data class FriendProfile(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?
    ) : Profile(), Parcelable

    @Parcelize
    data class FriendProfileWithMusic(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val description: String?,
        val musicTitle: String,
        val singer: String
    ) : Profile(), Parcelable

    @Parcelize
    data class FriendProfileWithBirth(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val birthMonth: Int,
        val birthDay: Int
    ) : Profile(), Parcelable
}
