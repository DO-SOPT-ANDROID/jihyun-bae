package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.local.ProfileRoomDataSource
import org.sopt.dosopttemplate.data.datasource.local.UserDataSource
import org.sopt.dosopttemplate.data.datasource.remote.AuthDataSource
import org.sopt.dosopttemplate.data.datasource.remote.ReqresDataSource
import org.sopt.dosopttemplate.data.datasourceimpl.local.ProfileRoomDataSourceImpl
import org.sopt.dosopttemplate.data.datasourceimpl.local.UserDataSourceImpl
import org.sopt.dosopttemplate.data.datasourceimpl.remote.AuthDataSourceImpl
import org.sopt.dosopttemplate.data.datasourceimpl.remote.ReqresDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Singleton
    @Binds
    abstract fun bindProfileRoomDataSource(profileRoomDataSourceImpl: ProfileRoomDataSourceImpl): ProfileRoomDataSource

    @Singleton
    @Binds
    abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Singleton
    @Binds
    abstract fun bindReqresDataSource(reqresDataSourceImpl: ReqresDataSourceImpl): ReqresDataSource
}