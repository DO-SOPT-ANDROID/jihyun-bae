package org.sopt.dosopttemplate.presentation.auth

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.presentation.model.UserInfo

class AuthViewModel() : ViewModel() {
    var userInfo: UserInfo? = null

    fun isSignUpEnabled(userInfo: UserInfo): Boolean {
        return with(userInfo) {
            id.length in MIN_ID_LENGTH..MAX_ID_LENGTH &&
                    password.length in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH &&
                    nickname.isNotBlank() &&
                    mbti.matches(Regex(MBTI_PATTERN))
        }
    }

    companion object {
        private const val MIN_ID_LENGTH = 6
        private const val MAX_ID_LENGTH = 10
        private const val MIN_PASSWORD_LENGTH = 8
        private const val MAX_PASSWORD_LENGTH = 12
        private const val MBTI_PATTERN = "^[EI][NS][FT][JP]\$"
    }
}