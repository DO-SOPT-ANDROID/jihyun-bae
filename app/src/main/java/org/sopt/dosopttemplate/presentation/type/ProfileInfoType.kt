package org.sopt.dosopttemplate.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.dosopttemplate.R

enum class ProfileInfoType(
    @DrawableRes val backgroundRes: Int,
    @StringRes val contextRes: Int?,
    @DrawableRes val iconRes: Int
) {
    DESCRIPTION(
        backgroundRes = R.drawable.shape_gray_300_line_12_rect,
        contextRes = R.string.home_profile_info_type_description,
        iconRes = R.drawable.ic_add_gray_400_24
    ),
    MUSIC(
        backgroundRes = R.drawable.shape_main_4_line_12_rect,
        contextRes = null,
        iconRes = R.drawable.ic_music_main_4_24
    ),
    BIRTH(
        backgroundRes = R.drawable.shape_point_3_line_12_rect,
        contextRes = R.string.home_profile_info_type_birth,
        iconRes = R.drawable.ic_gift_point_3_24
    )
}
