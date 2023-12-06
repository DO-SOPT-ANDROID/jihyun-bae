package org.sopt.dosopttemplate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.model.UserInfo
import org.sopt.dosopttemplate.domain.repository.AuthRepository

class GetUserInfoUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userId: Int): Flow<UserInfo> =
        authRepository.getUserInfo(userId = userId)
}