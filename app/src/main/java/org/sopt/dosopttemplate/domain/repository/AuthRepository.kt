package org.sopt.dosopttemplate.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.model.UserData
import org.sopt.dosopttemplate.domain.model.UserInfo

interface AuthRepository {
    suspend fun signUp(
        username: String,
        nickname: String,
        password: String
    ): Flow<Unit>

    suspend fun signIn(
        username: String,
        password: String
    ): Flow<UserData>

    suspend fun getUserInfo(
        userId: Int
    ): Flow<UserInfo>
}