package org.sopt.dosopttemplate.data.datasource.remote

import org.sopt.dosopttemplate.data.model.remote.response.ResponseListUsersDto

interface ReqresDataSource {
    suspend fun getListUsers(page: Int): ResponseListUsersDto
}