package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.local.DataSource.UserDataSource
import org.sopt.dosopttemplate.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override fun setUserId(id: Int) {
        userDataSource.setUserId(id)
    }

    override fun getUserId(): Int = userDataSource.getUserId()

    override fun setAutoLogin(isAutoLogin: Boolean) {
        userDataSource.setAutoLogin(isAutoLogin)
    }

    override fun getAutoLogin() = userDataSource.getAutoLoogin()

    override fun clearUserDataSource() {
        userDataSource.clearUserDataSource()
    }

}