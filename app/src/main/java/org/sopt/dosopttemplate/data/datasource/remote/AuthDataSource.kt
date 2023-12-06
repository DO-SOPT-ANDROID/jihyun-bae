package org.sopt.dosopttemplate.data.datasource.remote

import org.sopt.dosopttemplate.data.model.remote.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.remote.response.ResponseGetUserInfoDto
import org.sopt.dosopttemplate.data.model.remote.response.ResponseSignInDto

interface AuthDataSource {
    suspend fun signUp(requestSignUpDto: RequestSignUpDto): Unit
    suspend fun signIn(requestSignInDto: RequestSignInDto): ResponseSignInDto
    suspend fun getUserInfo(memberId: Int): ResponseGetUserInfoDto
}