package org.sopt.dosopttemplate.data.model.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.model.UserInfo

@Serializable
data class ResponseGetUserInfoDto(
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String
) {
    fun toUserInfo() = UserInfo(
        username = this.username,
        nickname = this.nickname
    )
}