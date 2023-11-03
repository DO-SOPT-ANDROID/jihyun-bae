package org.sopt.dosopttemplate.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.sopt.dosopttemplate.domain.model.Profile

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "profile_type")
    val profileType: ProfileType,
    @ColumnInfo(name = "profile_image")
    val profileImage: String,
    val name: String,
    val description: String? = null,
    val musicTitle: String? = null,
    val singer: String? = null,
    val birthMonth: Int? = null,
    val birthDay: Int? = null
) {
    enum class ProfileType {
        MY_PROFILE,
        FRIEND_PROFILE,
        FRIEND_PROFILE_WITH_MUSIC,
        FRIEND_PROFILE_WITH_BIRTH
    }

    fun toMyProfile() = Profile.MyProfile(
        id = this.id,
        profileImage = this.profileImage,
        name = this.name,
        description = this.description
    )

    fun toFriendProfile() = Profile.FriendProfile(
        id = this.id,
        profileImage = this.profileImage,
        name = this.name,
        description = this.description
    )

    fun toFriendProfileWithMusic() = Profile.FriendProfileWithMusic(
        id = this.id,
        profileImage = this.profileImage,
        name = this.name,
        description = this.description,
        musicTitle = musicTitle ?: "",
        singer = singer ?: ""
    )

    fun toFriendProfileWithBirth() = Profile.FriendProfileWithBirth(
        id = this.id,
        profileImage = this.profileImage,
        name = this.name,
        birthMonth = this.birthMonth ?: 0,
        birthDay = this.birthDay ?: 0
    )
}