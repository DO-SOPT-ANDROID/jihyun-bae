package org.sopt.dosopttemplate.presentation.home

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemHomeProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.type.ProfileInfoType

class ProfileViewHolder(
    private val binding: ItemHomeProfileBinding,
    private val context: Context,
    private val moveToProfileDetail: (Profile) -> Unit,
    private val showDeleteProfileDialog: (Profile) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(profile: Profile) {
        when (profile) {
            is Profile.MyProfile -> {
                binding.run {
                    ivProfile.load(profile.profileImage)
                    tvProfileName.text = profile.name
                    tvProfileDescription.text = profile.description
                    if (profile.description.isNullOrEmpty()) tvProfileDescription.visibility =
                        View.GONE
                    ivProfileBirth.visibility = View.INVISIBLE

                    with(includeProfileInfo) {
                        root.visibility = View.VISIBLE
                        layoutLandscapeProfileInfo.setBackgroundResource(ProfileInfoType.DESCRIPTION.landscapeBackgroundRes)
                        ProfileInfoType.DESCRIPTION.contextRes?.let {
                            tvLandscapeProfileInfoContext.setText(
                                it
                            )
                        }
                        ivLandscapeProfileInfoIcon.setImageResource(ProfileInfoType.DESCRIPTION.iconRes)
                    }

                    root.setOnClickListener {
                        moveToProfileDetail(profile)
                    }
                }
            }

            is Profile.FriendProfile -> {
                binding.run {
                    ivProfile.load(profile.profileImage)
                    tvProfileName.text = profile.name
                    tvProfileDescription.text = profile.description
                    if (profile.description.isNullOrEmpty()) tvProfileDescription.visibility =
                        View.GONE
                    ivProfileBirth.visibility = View.INVISIBLE
                    includeProfileInfo.root.visibility = View.INVISIBLE

                    root.setOnClickListener {
                        moveToProfileDetail(profile)
                    }

                    root.setOnLongClickListener {
                        showDeleteProfileDialog(profile)
                        return@setOnLongClickListener true
                    }
                }
            }

            is Profile.FriendProfileWithMusic -> {
                binding.run {
                    ivProfile.load(profile.profileImage)
                    tvProfileName.text = profile.name
                    tvProfileDescription.text = profile.description
                    if (profile.description.isNullOrEmpty()) tvProfileDescription.visibility =
                        View.GONE
                    ivProfileBirth.visibility = View.INVISIBLE

                    with(includeProfileInfo) {
                        root.visibility = View.VISIBLE
                        layoutLandscapeProfileInfo.setBackgroundResource(ProfileInfoType.MUSIC.landscapeBackgroundRes)
                        tvLandscapeProfileInfoContext.text = context.getString(
                            R.string.home_music,
                            profile.musicTitle,
                            profile.singer
                        )
                        ivLandscapeProfileInfoIcon.setImageResource(ProfileInfoType.MUSIC.iconRes)
                    }

                    root.setOnClickListener {
                        moveToProfileDetail(profile)
                    }

                    root.setOnLongClickListener {
                        showDeleteProfileDialog(profile)
                        return@setOnLongClickListener true
                    }
                }
            }

            is Profile.FriendProfileWithBirth -> {
                binding.run {
                    ivProfile.load(profile.profileImage)
                    tvProfileName.text = profile.name
                    tvProfileDescription.text = context.getString(
                        R.string.home_birth,
                        profile.birthMonth,
                        profile.birthDay
                    )
                    ivProfileBirth.visibility = View.VISIBLE

                    with(includeProfileInfo) {
                        root.visibility = View.VISIBLE
                        layoutLandscapeProfileInfo.setBackgroundResource(ProfileInfoType.BIRTH.landscapeBackgroundRes)
                        ProfileInfoType.BIRTH.contextRes?.let {
                            tvLandscapeProfileInfoContext.setText(
                                it
                            )
                        }
                        ivLandscapeProfileInfoIcon.setImageResource(ProfileInfoType.BIRTH.iconRes)
                    }

                    root.setOnClickListener {
                        moveToProfileDetail(profile)
                    }

                    root.setOnLongClickListener {
                        showDeleteProfileDialog(profile)
                        return@setOnLongClickListener true
                    }
                }
            }
        }
    }
}