package org.sopt.dosopttemplate.domain.model

import org.sopt.dosopttemplate.data.model.local.LocalUser
import org.sopt.dosopttemplate.presentation.model.User

data class User(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: String
) {
    fun toLocalUser() = LocalUser(
        id = this.id,
        password = this.password,
        nickname = this.nickname,
        mbti = this.mbti
    )

    fun toParcelizeUser() = User(
        id = this.id,
        password = this.password,
        nickname = this.nickname,
        mbti = this.mbti
    )
}