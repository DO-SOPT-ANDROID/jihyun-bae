package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.local.DataSource.UserDataSource
import org.sopt.dosopttemplate.domain.model.User
import org.sopt.dosopttemplate.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override fun setUser(user: User) {
        userDataSource.setUserInfo(user.toLocalUser())
    }

    override fun getUserInfo() = userDataSource.getUserInfo().toUser()

    override fun setAutoLogin(isAutoLogin: Boolean) {
        userDataSource.setAutoLogin(isAutoLogin)
    }

    override fun getAutoLogin() = userDataSource.getAutoLogin()

    override fun clearUserDataSource() {
        userDataSource.clearUserDataSource()
    }

}