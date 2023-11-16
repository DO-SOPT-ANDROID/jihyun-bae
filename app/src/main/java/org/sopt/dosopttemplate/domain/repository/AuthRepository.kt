package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.UserData
import org.sopt.dosopttemplate.domain.model.UserInfo

interface AuthRepository {
    suspend fun signUp(
        username: String,
        nickname: String,
        password: String
    ): Result<Unit>

    suspend fun signIn(
        username: String,
        password: String
    ): Result<UserData>

    suspend fun getUserInfo(
        userId: Int
    ): Result<UserInfo>
}