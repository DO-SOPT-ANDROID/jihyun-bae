package org.sopt.dosopttemplate.data.datasource.remote

import org.sopt.dosopttemplate.data.model.remote.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.remote.response.ResponseGetUserInfoDto
import org.sopt.dosopttemplate.data.model.remote.response.ResponseSignInDto
import org.sopt.dosopttemplate.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun signUp(
        requestSignUpDto: RequestSignUpDto
    ): Unit = authService.signUp(requestSignUpDto)

    suspend fun signIn(
        requestSignInDto: RequestSignInDto
    ): ResponseSignInDto = authService.signIn(requestSignInDto)

    suspend fun getUserInfo(
        memberId: Int
    ): ResponseGetUserInfoDto = authService.getUserInfo(memberId)
}