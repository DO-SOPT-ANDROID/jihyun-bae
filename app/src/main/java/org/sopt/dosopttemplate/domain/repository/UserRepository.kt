package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.User

interface UserRepository {
    fun setUser(user: User)
    fun getUserInfo(): User
    fun setAutoLogin(isAutoLogin: Boolean)
    fun getAutoLogin(): Boolean
    fun clearUserDataSource()
}