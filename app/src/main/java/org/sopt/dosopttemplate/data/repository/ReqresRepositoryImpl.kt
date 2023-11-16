package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.remote.ReqresDataSource
import org.sopt.dosopttemplate.domain.model.ReqresUser
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import javax.inject.Inject

class ReqresRepositoryImpl @Inject constructor(
    private val reqresDataSource: ReqresDataSource,
) : ReqresRepository {
    override suspend fun getListUsers(page: Int): Result<List<ReqresUser>> = runCatching {
        reqresDataSource.getListUsers(page).toReqresUser()
    }
}