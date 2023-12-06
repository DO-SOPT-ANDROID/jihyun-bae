package org.sopt.dosopttemplate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.repository.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, nickname: String, password: String): Flow<Unit> =
        authRepository.signUp(username = username, nickname = nickname, password = password)
}