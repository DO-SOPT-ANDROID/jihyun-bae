package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.repository.UserRepository

class ClearUserDataSourceUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.clearUserDataSource()
}