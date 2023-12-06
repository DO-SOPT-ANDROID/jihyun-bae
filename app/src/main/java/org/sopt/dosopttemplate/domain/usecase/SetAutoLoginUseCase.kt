package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.repository.UserRepository

class SetAutoLoginUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(isAutoLogin: Boolean) =
        userRepository.setAutoLogin(isAutoLogin = isAutoLogin)
}