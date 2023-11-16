package org.sopt.dosopttemplate.domain.repository

interface UserRepository {
    fun setUserId(id: Int)
    fun getUserId(): Int
    fun setAutoLogin(isAutoLogin: Boolean)
    fun getAutoLogin(): Boolean
    fun clearUserDataSource()
}