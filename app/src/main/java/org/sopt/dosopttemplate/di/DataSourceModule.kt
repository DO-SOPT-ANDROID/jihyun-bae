package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.local.DataSource.DummyDataSource
import org.sopt.dosopttemplate.data.datasource.local.DataSource.UserDataSource
import org.sopt.dosopttemplate.data.datasource.local.DataSourceImpl.DummyDataSourceImpl
import org.sopt.dosopttemplate.data.datasource.local.DataSourceImpl.UserDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Singleton
    @Binds
    abstract fun bindDummyDataSource(dummyDataSourceImpl: DummyDataSourceImpl): DummyDataSource
}