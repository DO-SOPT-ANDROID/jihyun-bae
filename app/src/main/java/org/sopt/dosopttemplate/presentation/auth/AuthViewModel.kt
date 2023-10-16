package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.model.User
import org.sopt.dosopttemplate.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    var user: User? = null

    fun isSignUpEnabled(userInfo: User): Boolean {
        return with(userInfo) {
            id.length in MIN_ID_LENGTH..MAX_ID_LENGTH &&
                    password.length in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH &&
                    nickname.isNotBlank() &&
                    mbti.matches(Regex(MBTI_PATTERN))
        }
    }

    fun isSignInEnabled(inputId: String, inputPassword: String): Boolean {
        return user?.run {
            id == inputId && password == inputPassword
        } ?: false
    }

    fun setAutoLogin() {
        with(userRepository) {
            setAutoLogin(true)
            user?.let {
                setUser(it)
            }
        }
    }

    fun getAutoLogin() = userRepository.getAutoLogin()

    companion object {
        private const val MIN_ID_LENGTH = 6
        private const val MAX_ID_LENGTH = 10
        private const val MIN_PASSWORD_LENGTH = 8
        private const val MAX_PASSWORD_LENGTH = 12
        private const val MBTI_PATTERN = "^[EI][NS][FT][JP]\$"
    }
}