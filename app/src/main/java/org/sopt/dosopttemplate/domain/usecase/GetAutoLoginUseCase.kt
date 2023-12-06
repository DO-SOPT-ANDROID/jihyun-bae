package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.repository.UserRepository

class GetAutoLoginUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.getAutoLogin()
}