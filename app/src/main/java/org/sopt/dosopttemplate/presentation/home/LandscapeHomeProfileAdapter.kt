package org.sopt.dosopttemplate.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemHomeProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.type.ProfileInfoType
import org.sopt.dosopttemplate.util.ItemDiffCallback

class LandscapeHomeProfileAdapter(
    private val moveToProfileDetail: (Profile) -> Unit
) : ListAdapter<Profile, LandscapeHomeProfileAdapter.ProfileViewHolder>(
    ItemDiffCallback<Profile>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new }
    )
) {
    class ProfileViewHolder(
        private val binding: ItemHomeProfileBinding,
        private val context: Context,
        private val moveToProfileDetail: (Profile) -> Unit
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

                        with(includeProfileInfo) {
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

                        root.setOnClickListener {
                            moveToProfileDetail(profile)
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

                        with(includeProfileInfo) {
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
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val binding =
            ItemHomeProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding, parent.context, moveToProfileDetail)
    }

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        holder.onBind(currentList[position])
    }
}