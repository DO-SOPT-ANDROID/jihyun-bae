package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.model.remote.request.RequestSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    suspend fun signUp(
        @Body requestSignUpDto: RequestSignUpDto
    )
}