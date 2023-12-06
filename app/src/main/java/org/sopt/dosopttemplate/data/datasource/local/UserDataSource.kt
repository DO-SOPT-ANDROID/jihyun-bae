package org.sopt.dosopttemplate.data.datasource.local

interface UserDataSource {
    fun setUserId(id: Int)
    fun getUserId(): Int
    fun setAutoLogin(isAutoLogin: Boolean)
    fun getAutoLogin(): Boolean
    fun clearUserDataSource()
}