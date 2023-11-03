package org.sopt.dosopttemplate.presentation.profileDetail

import android.os.Bundle
import android.view.View
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityProfileDetailBinding
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.PROFILE
import org.sopt.dosopttemplate.presentation.model.Profile
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.getCompatibleParcelableExtra

class ProfileDetailActivity :
    BindingActivity<ActivityProfileDetailBinding>(R.layout.activity_profile_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        intent.getCompatibleParcelableExtra<Profile>(PROFILE)?.let { profile ->
            when (profile) {
                is Profile.MyProfile -> {
                    with(binding) {
                        ivProfileDetailProfile.load(profile.profileImage)
                        tvProfileDetailName.text = profile.name
                        tvProfileDetailDescription.text = profile.description
                        if (profile.description.isNullOrEmpty()) tvProfileDetailDescription.visibility =
                            View.GONE
                    }
                }

                is Profile.FriendProfile -> {
                    with(binding) {
                        ivProfileDetailProfile.load(profile.profileImage)
                        tvProfileDetailName.text = profile.name
                        tvProfileDetailDescription.text = profile.description
                        if (profile.description.isNullOrEmpty()) tvProfileDetailDescription.visibility =
                            View.GONE
                    }
                }

                is Profile.FriendProfileWithMusic -> {
                    with(binding) {
                        ivProfileDetailProfile.load(profile.profileImage)
                        tvProfileDetailName.text = profile.name
                        tvProfileDetailDescription.text = profile.description
                        if (profile.description.isNullOrEmpty()) tvProfileDetailDescription.visibility =
                            View.GONE
                        ivProfileDetailMusic.visibility = View.VISIBLE
                        tvFriendProfileMusic.visibility = View.VISIBLE
                        tvFriendProfileMusic.text = getString(
                            R.string.home_music,
                            profile.musicTitle,
                            profile.singer
                        )
                    }
                }

                is Profile.FriendProfileWithBirth -> {
                    with(binding) {
                        ivProfileDetailProfile.load(profile.profileImage)
                        ivProfileDetailBirth.visibility = View.VISIBLE
                        tvProfileDetailName.text = profile.name
                        tvProfileDetailDescription.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun addListeners() {
        binding.ivProfileDetailArrowLeft.setOnClickListener {
            finish()
        }
    }
}