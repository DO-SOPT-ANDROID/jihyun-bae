package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.ReqresUser

interface ReqresRepository {
    suspend fun getListUsers(page: Int): Result<List<ReqresUser>>
}