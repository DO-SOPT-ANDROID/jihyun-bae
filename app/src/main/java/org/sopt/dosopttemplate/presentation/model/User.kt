package org.sopt.dosopttemplate.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.sopt.dosopttemplate.domain.model.User

@Parcelize
data class User(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: String
) : Parcelable {
    fun toUser() = User(
        id = this.id,
        password = this.password,
        nickname = this.nickname,
        mbti = this.mbti
    )
}