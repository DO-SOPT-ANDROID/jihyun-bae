package org.sopt.dosopttemplate.presentation.home

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemHomeFriendProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.type.ProfileInfoType

class FriendProfileViewHolder(
    private val binding: ItemHomeFriendProfileBinding,
    private val context: Context,
    private val moveToProfileDetail: (Profile) -> Unit,
    private val showDeleteProfileDialog: (Profile) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(profile: Profile) {
        when (profile) {
            is Profile.FriendProfile -> {
                binding.run {
                    ivFriendProfile.load(profile.profileImage)
                    tvFriendProfileName.text = profile.name
                    tvFriendProfileDescription.text = profile.description
                    if (profile.description.isNullOrEmpty()) tvFriendProfileDescription.visibility =
                        View.GONE

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
                    ivFriendProfile.load(profile.profileImage)
                    tvFriendProfileName.text = profile.name
                    tvFriendProfileDescription.text = profile.description
                    if (profile.description.isNullOrEmpty()) tvFriendProfileDescription.visibility =
                        View.GONE

                    with(includeFriendProfileInfo) {
                        layoutPortraitProfileInfo.setBackgroundResource(ProfileInfoType.MUSIC.landscapeBackgroundRes)
                        tvPortraitProfileInfoContext.text = context.getString(
                            R.string.home_music,
                            profile.musicTitle,
                            profile.singer
                        )
                        ivPortraitProfileInfoIcon.setImageResource(ProfileInfoType.MUSIC.iconRes)
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
                    ivFriendProfile.load(profile.profileImage)
                    tvFriendProfileName.text = profile.name
                    tvFriendProfileDescription.text = context.getString(
                        R.string.home_birth,
                        profile.birthMonth,
                        profile.birthDay
                    )
                    ivFriendProfileBirth.visibility = View.VISIBLE

                    with(includeFriendProfileInfo) {
                        layoutPortraitProfileInfo.setBackgroundResource(ProfileInfoType.BIRTH.landscapeBackgroundRes)
                        ProfileInfoType.BIRTH.contextRes?.let {
                            tvPortraitProfileInfoContext.setText(
                                it
                            )
                        }
                        ivPortraitProfileInfoIcon.setImageResource(ProfileInfoType.BIRTH.iconRes)
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

            else -> throw Exception("unknown type!!")
        }
    }
}