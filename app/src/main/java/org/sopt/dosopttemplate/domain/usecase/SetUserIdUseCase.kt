package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.repository.UserRepository

class SetUserIdUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(id: Int) = userRepository.setUserId(id = id)
}