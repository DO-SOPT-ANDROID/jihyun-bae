package org.sopt.dosopttemplate.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.local.Database.ProfileRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideProfileRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ProfileRoomDatabase::class.java, "profile_database.db")
            .build()

    @Singleton
    @Provides
    fun provideProfileDao(profileRoomDatabase: ProfileRoomDatabase) =
        profileRoomDatabase.profileDao()
}