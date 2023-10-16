package org.sopt.dosopttemplate.data.model.local

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.sopt.dosopttemplate.domain.model.User

@Serializable
data class LocalUser(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: String
) {
    fun toJsonString() = Json.encodeToString(this)

    fun toUser() = User(
        id = this.id,
        password = this.password,
        nickname = this.nickname,
        mbti = this.mbti
    )
}
