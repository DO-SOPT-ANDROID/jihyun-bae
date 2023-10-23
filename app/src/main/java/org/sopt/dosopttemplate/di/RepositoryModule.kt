package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.repository.DummyRepositoryImpl
import org.sopt.dosopttemplate.data.repository.UserRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.DummyRepository
import org.sopt.dosopttemplate.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Singleton
    @Binds
    fun bindDummyRepository(
        dummyRepositoryImpl: DummyRepositoryImpl
    ): DummyRepository
}