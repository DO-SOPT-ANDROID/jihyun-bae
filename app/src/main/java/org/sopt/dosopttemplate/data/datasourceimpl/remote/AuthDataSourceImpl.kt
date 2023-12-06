package org.sopt.dosopttemplate.data.datasourceimpl.remote

import org.sopt.dosopttemplate.data.datasource.remote.AuthDataSource
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.remote.response.ResponseGetUserInfoDto
import org.sopt.dosopttemplate.data.model.remote.response.ResponseSignInDto
import org.sopt.dosopttemplate.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthDataSource {
    override suspend fun signUp(
        requestSignUpDto: RequestSignUpDto
    ): Unit = authService.signUp(requestSignUpDto)

    override suspend fun signIn(
        requestSignInDto: RequestSignInDto
    ): ResponseSignInDto = authService.signIn(requestSignInDto)

    override suspend fun getUserInfo(
        memberId: Int
    ): ResponseGetUserInfoDto = authService.getUserInfo(memberId)
}