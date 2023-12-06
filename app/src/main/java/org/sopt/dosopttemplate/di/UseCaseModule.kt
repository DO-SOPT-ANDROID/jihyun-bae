package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import org.sopt.dosopttemplate.domain.repository.UserRepository
import org.sopt.dosopttemplate.domain.usecase.ClearUserDataSourceUseCase
import org.sopt.dosopttemplate.domain.usecase.GetAutoLoginUseCase
import org.sopt.dosopttemplate.domain.usecase.GetListUsersUseCase
import org.sopt.dosopttemplate.domain.usecase.GetUserIdUseCase
import org.sopt.dosopttemplate.domain.usecase.GetUserInfoUseCase
import org.sopt.dosopttemplate.domain.usecase.SetAutoLoginUseCase
import org.sopt.dosopttemplate.domain.usecase.SetUserIdUseCase
import org.sopt.dosopttemplate.domain.usecase.SignInUseCase
import org.sopt.dosopttemplate.domain.usecase.SignUpUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase =
        SignUpUseCase(authRepository)

    @Singleton
    @Provides
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase =
        SignInUseCase(authRepository)

    @Singleton
    @Provides
    fun provideGetUserInfoUseCase(authRepository: AuthRepository): GetUserInfoUseCase =
        GetUserInfoUseCase(authRepository)

    @Singleton
    @Provides
    fun provideGetListUsersUseCase(reqresRepository: ReqresRepository): GetListUsersUseCase =
        GetListUsersUseCase(reqresRepository)

    @Singleton
    @Provides
    fun provideSetUserIdUseCase(userRepository: UserRepository): SetUserIdUseCase =
        SetUserIdUseCase(userRepository)

    @Singleton
    @Provides
    fun provideGetUserIdUseCase(userRepository: UserRepository): GetUserIdUseCase =
        GetUserIdUseCase(userRepository)

    @Singleton
    @Provides
    fun provideSetAutoLoginUseCase(userRepository: UserRepository): SetAutoLoginUseCase =
        SetAutoLoginUseCase(userRepository)

    @Singleton
    @Provides
    fun provideGetAutoLoginUseCase(userRepository: UserRepository): GetAutoLoginUseCase =
        GetAutoLoginUseCase(userRepository)

    @Singleton
    @Provides
    fun provideClearUserDataSourceUseCase(userRepository: UserRepository): ClearUserDataSourceUseCase =
        ClearUserDataSourceUseCase(userRepository)
}