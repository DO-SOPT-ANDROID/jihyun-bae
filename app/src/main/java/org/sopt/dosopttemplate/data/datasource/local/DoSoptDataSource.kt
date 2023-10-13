package org.sopt.dosopttemplate.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DoSoptDataSource @Inject constructor(@ApplicationContext context: Context) {
    private val dataStore: SharedPreferences =
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    var isLogin: Boolean
        set(value) = dataStore.edit { putBoolean(IS_LOGIN, value) }
        get() = dataStore.getBoolean(IS_LOGIN, false)

    var userId: String
        set(value) = dataStore.edit { putString(USER_ID, value) }
        get() = dataStore.getString(USER_ID, "") ?: ""

    var userNickname: String
        set(value) = dataStore.edit { putString(USER_NICKNAME, value) }
        get() = dataStore.getString(USER_NICKNAME, "") ?: ""

    var userMBTI: String
        set(value) = dataStore.edit { putString(USER_MBTI, value) }
        get() = dataStore.getString(USER_MBTI, "") ?: ""

    fun clear() {
        dataStore.edit {
            clear()
        }
    }

    companion object {
        const val FILE_NAME = "doSoptSharedPreferences"
        const val IS_LOGIN = "isLogin"
        const val USER_ID = "userId"
        const val USER_NICKNAME = "userNickname"
        const val USER_MBTI = "userMBTI"
    }
}