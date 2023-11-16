package org.sopt.dosopttemplate.data.datasource.remote

import org.sopt.dosopttemplate.data.model.remote.response.ResponseListUsersDto
import org.sopt.dosopttemplate.data.service.ReqresService
import javax.inject.Inject

class ReqresDataSource @Inject constructor(
    private val reqresService: ReqresService
) {
    suspend fun getListUsers(page: Int): ResponseListUsersDto =
        reqresService.getListUsers(page)
}