package org.sopt.dosopttemplate.presentation.friend

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.databinding.ItemFriendMyProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.type.ProfileInfoType

class MyProfileViewHolder(
    private val binding: ItemFriendMyProfileBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(myProfile: Profile.MyProfile) {
        binding.run {
            ivMyProfile.load(myProfile.profileImage)
            tvMyProfileName.text = myProfile.name
            tvMyProfileDescription.text = myProfile.description
            if (myProfile.description.isNullOrEmpty()) tvMyProfileDescription.visibility =
                View.GONE

            with(includeMyProfileInfo) {
                layoutPortraitProfileInfo.setBackgroundResource(ProfileInfoType.DESCRIPTION.portraitBackgroundRes)
                ProfileInfoType.DESCRIPTION.contextRes?.let {
                    tvPortraitProfileInfoContext.setText(
                        it
                    )
                }
                ivPortraitProfileInfoIcon.setImageResource(ProfileInfoType.DESCRIPTION.iconRes)
            }
        }
    }
}