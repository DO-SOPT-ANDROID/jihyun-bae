package org.sopt.dosopttemplate.data.datasource.local.DataSource

interface UserDataSource {
    fun setUserId(id: Int)
    fun getUserId(): Int
    fun setAutoLogin(isAutoLogin: Boolean)
    fun getAutoLoogin(): Boolean
    fun clearUserDataSource()
}