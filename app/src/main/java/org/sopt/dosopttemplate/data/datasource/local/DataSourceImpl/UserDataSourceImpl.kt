package org.sopt.dosopttemplate.data.datasource.local.DataSourceImpl

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.sopt.dosopttemplate.data.datasource.local.DataSource.UserDataSource
import org.sopt.dosopttemplate.data.model.local.LocalUser
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val dataStore: SharedPreferences
) : UserDataSource {
    override fun setUserInfo(user: LocalUser) {
        dataStore.edit { putString(USER, user.toJsonString()) }
    }

    override fun getUserInfo() =
        Json.decodeFromString<LocalUser>(dataStore.getString(USER, "") ?: "")

    override fun setAutoLogin(isAutoLogin: Boolean) {
        dataStore.edit { putBoolean(AUTO_LOGIN, isAutoLogin) }
    }

    override fun getAutoLogin() = dataStore.getBoolean(AUTO_LOGIN, false)

    override fun clearUserDataSource() {
        dataStore.edit { clear() }
    }

    companion object {
        const val FILE_NAME = "doSopt"
        const val USER = "user"
        const val AUTO_LOGIN = "autoLogin"
    }
}