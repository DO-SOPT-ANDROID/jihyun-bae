package org.sopt.dosopttemplate.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sopt.dosopttemplate.data.datasource.remote.AuthDataSource
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.domain.model.UserData
import org.sopt.dosopttemplate.domain.model.UserInfo
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun signUp(
        username: String,
        nickname: String,
        password: String
    ): Flow<Unit> {
        return flow {
            val result = runCatching {
                authDataSource.signUp(
                    RequestSignUpDto(
                        username = username,
                        nickname = nickname,
                        password = password
                    )
                )
            }
            emit(result.getOrThrow())
        }
    }

    override suspend fun signIn(username: String, password: String): Flow<UserData> {
        return flow {
            val result = runCatching {
                authDataSource.signIn(
                    RequestSignInDto(
                        username = username,
                        password = password
                    )
                ).toUserData()
            }
            emit(result.getOrThrow())
        }
    }

    override suspend fun getUserInfo(memberId: Int): Flow<UserInfo> {
        return flow {
            val result = runCatching {
                authDataSource.getUserInfo(memberId).toUserInfo()
            }
            emit(result.getOrThrow())
        }
    }
}