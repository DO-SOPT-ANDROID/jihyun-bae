package org.sopt.dosopttemplate.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dosopttemplate.R

enum class ProfileInfoType(
    @DrawableRes val portraitBackgroundRes: Int,
    @DrawableRes val landscapeBackgroundRes: Int,
    @StringRes val contextRes: Int?,
    @DrawableRes val iconRes: Int
) {
    DESCRIPTION(
        portraitBackgroundRes = R.drawable.shape_gray_300_line_12_rect,
        landscapeBackgroundRes = R.drawable.shape_gray_300_line_50_rect,
        contextRes = R.string.friend_profile_info_type_description,
        iconRes = R.drawable.ic_add_gray_400_24
    ),
    MUSIC(
        portraitBackgroundRes = R.drawable.shape_main_4_line_12_rect,
        landscapeBackgroundRes = R.drawable.shape_main_4_line_50_rect,
        contextRes = null,
        iconRes = R.drawable.ic_music_main_4_24
    ),
    BIRTH(
        portraitBackgroundRes = R.drawable.shape_point_3_line_12_rect,
        landscapeBackgroundRes = R.drawable.shape_point_3_line_50_rect,
        contextRes = R.string.friend_profile_info_type_birth,
        iconRes = R.drawable.ic_gift_point_3_24
    )
}
