package org.sopt.dosopttemplate.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: String
) : Parcelable