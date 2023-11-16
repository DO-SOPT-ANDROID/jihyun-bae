package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.remote.AuthDataSource
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.domain.model.UserData
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun signUp(
        username: String,
        nickname: String,
        password: String
    ): Result<Unit> = runCatching {
        authDataSource.signUp(
            RequestSignUpDto(
                username = username,
                nickname = nickname,
                password = password
            )
        )
    }

    override suspend fun signIn(username: String, password: String): Result<UserData> =
        runCatching {
            authDataSource.signIn(
                RequestSignInDto(
                    username = username,
                    password = password
                )
            ).toUserData()
        }
}