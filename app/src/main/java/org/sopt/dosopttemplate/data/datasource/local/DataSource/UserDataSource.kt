package org.sopt.dosopttemplate.data.datasource.local.DataSource

import org.sopt.dosopttemplate.data.model.local.LocalUser

interface UserDataSource {
    fun setUserInfo(user: LocalUser)
    fun getUserInfo(): LocalUser
    fun setAutoLogin(isAutoLogin: Boolean)
    fun getAutoLogin(): Boolean
    fun clearUserDataSource()
}