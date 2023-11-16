package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.service.AuthService
import org.sopt.dosopttemplate.data.service.ReqresService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideAuthService(@Sopt retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideReqresService(retrofit: Retrofit): ReqresService =
        retrofit.create(ReqresService::class.java)
}