package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.model.remote.request.RequestSignInDto
import org.sopt.dosopttemplate.data.model.remote.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.remote.response.ResponseSignInDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    suspend fun signUp(
        @Body requestSignUpDto: RequestSignUpDto
    )

    @POST("api/v1/members/sign-in")
    suspend fun signIn(
        @Body requestSignInDto: RequestSignInDto
    ): ResponseSignInDto
}