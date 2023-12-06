package org.sopt.dosopttemplate.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sopt.dosopttemplate.domain.model.ReqresUser
import org.sopt.dosopttemplate.domain.repository.ReqresRepository

class GetListUsersUseCase(
    private val reqresRepository: ReqresRepository
) {
    suspend operator fun invoke(page: Int): Flow<List<ReqresUser>> =
        reqresRepository.getListUsers(page = page)
}