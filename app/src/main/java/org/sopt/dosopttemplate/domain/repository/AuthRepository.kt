package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.UserData

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
}