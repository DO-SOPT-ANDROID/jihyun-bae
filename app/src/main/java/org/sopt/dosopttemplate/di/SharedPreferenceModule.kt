package org.sopt.dosopttemplate.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.local.DataSourceImpl.UserDataSourceImpl.Companion.FILE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context) =
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
}