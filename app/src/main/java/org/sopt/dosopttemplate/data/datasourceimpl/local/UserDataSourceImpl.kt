package org.sopt.dosopttemplate.data.datasourceimpl.local

import android.content.SharedPreferences
import androidx.core.content.edit
import org.sopt.dosopttemplate.data.datasource.local.UserDataSource
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val dataStore: SharedPreferences
) : UserDataSource {
    override fun setUserId(id: Int) {
        dataStore.edit { putInt(USER_ID, id) }
    }

    override fun getUserId(): Int = dataStore.getInt(USER_ID, DEFAULT_USER_ID)

    override fun setAutoLogin(isAutoLogin: Boolean) {
        dataStore.edit { putBoolean(AUTO_LOGIN, isAutoLogin) }
    }

    override fun getAutoLogin(): Boolean = dataStore.getBoolean(AUTO_LOGIN, false)

    override fun clearUserDataSource() {
        dataStore.edit { clear() }
    }

    companion object {
        const val FILE_NAME = "doSopt"
        const val USER_ID = "userId"
        const val AUTO_LOGIN = "autoLogin"
        const val DEFAULT_USER_ID = 0
    }
}