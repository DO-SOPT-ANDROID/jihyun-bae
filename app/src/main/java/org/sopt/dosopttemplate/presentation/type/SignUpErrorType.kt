package org.sopt.dosopttemplate.presentation.type

import androidx.annotation.StringRes
import org.sopt.dosopttemplate.R

enum class SignUpErrorType(
    @StringRes val errorMessage: Int,
) {
    ID(R.string.sign_up_id_error),
    PASSWORD(R.string.sign_up_password_error),
    NICKNAME(R.string.sign_up_nickname_error),
    MBTI(R.string.sign_up_mbti_error)
}