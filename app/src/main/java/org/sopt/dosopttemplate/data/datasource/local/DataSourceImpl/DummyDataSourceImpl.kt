package org.sopt.dosopttemplate.data.datasource.local.DataSourceImpl

import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DataSource.DummyDataSource
import org.sopt.dosopttemplate.data.model.local.Profile
import javax.inject.Inject

class DummyDataSourceImpl @Inject constructor() : DummyDataSource {
    override val mockProfileList: List<Profile>
        get() = listOf(
            Profile.MyProfile(
                profileImage = R.drawable.ic_profile_24,
                name = "배지현",
                description = null
            ),
            Profile.FriendProfileWithMusic(
                profileImage = R.drawable.img_keroro,
                name = "케로로",
                description = "케로케로케로케로케로케로케로케로",
                musicTitle = "케로로 행진곡",
                singer = "케로로 소대"
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_dororo,
                name = "도로로",
                description = "ㅠㅠ"
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_giroro,
                name = "기로로",
                description = null
            ),
            Profile.FriendProfileWithBirth(
                profileImage = R.drawable.img_gunbbang,
                name = "건빵",
                birthMonth = 10,
                birthDay = 23
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_tamama,
                name = "타마마",
                description = "타마타마"
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_kururu,
                name = "쿠루루",
                description = null
            ),
            Profile.FriendProfileWithMusic(
                profileImage = R.drawable.img_karara,
                name = "카라라",
                description = null,
                musicTitle = "위풍당당 케로로",
                singer = "케로로 소대"
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_pururu,
                name = "푸루루",
                description = null
            ),
            Profile.FriendProfile(
                profileImage = R.drawable.img_garuru,
                name = "가루루",
                description = null
            )
        )
}