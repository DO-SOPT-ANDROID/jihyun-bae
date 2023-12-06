package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.repository.UserRepository

class GetUserIdUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Int = userRepository.getUserId()
}