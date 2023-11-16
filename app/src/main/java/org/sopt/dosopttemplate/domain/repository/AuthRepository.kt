package org.sopt.dosopttemplate.domain.repository

interface AuthRepository {
    suspend fun signUp(
        username: String,
        nickname: String,
        password: String
    ): Result<Unit>
}