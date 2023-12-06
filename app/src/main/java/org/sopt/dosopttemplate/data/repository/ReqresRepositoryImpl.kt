package org.sopt.dosopttemplate.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sopt.dosopttemplate.data.datasource.remote.ReqresDataSource
import org.sopt.dosopttemplate.domain.model.ReqresUser
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import javax.inject.Inject

class ReqresRepositoryImpl @Inject constructor(
    private val reqresDataSource: ReqresDataSource,
) : ReqresRepository {
    override suspend fun getListUsers(page: Int): Flow<List<ReqresUser>> {
        return flow {
            val result = runCatching {
                reqresDataSource.getListUsers(page).toReqresUser()
            }
            emit(result.getOrThrow())
        }
    }
}