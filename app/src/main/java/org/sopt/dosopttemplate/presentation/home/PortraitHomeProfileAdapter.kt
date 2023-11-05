package org.sopt.dosopttemplate.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemHomeMyProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.type.ProfileInfoType
import org.sopt.dosopttemplate.util.ItemDiffCallback

class PortraitHomeProfileAdapter(
    private val moveToProfileDetail: (Profile) -> Unit,
    private val showDeleteProfileDialog: (Profile) -> Unit
) : ListAdapter<Profile, RecyclerView.ViewHolder>(
    ItemDiffCallback<Profile>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.id == new.id }
    )
) {
    class MyProfileViewHolder(
        private val binding: ItemHomeMyProfileBinding
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        VIEW_TYPE_MY_PROFILE -> {
            MyProfileViewHolder(
                ItemHomeMyProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        VIEW_TYPE_FRIEND_PROFILE -> {
            FriendProfileViewHolder(
                ItemHomeFriendProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                parent.context,
                moveToProfileDetail,
                showDeleteProfileDialog
            )
        }

        else -> throw Exception("unknown type!!")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyProfileViewHolder -> holder.onBind(currentList[position] as Profile.MyProfile)
            is FriendProfileViewHolder -> holder.onBind(currentList[position])
        }
    }

    override fun getItemViewType(position: Int): Int = when (currentList[position]) {
        is Profile.MyProfile -> VIEW_TYPE_MY_PROFILE
        else -> VIEW_TYPE_FRIEND_PROFILE
    }

    companion object {
        const val VIEW_TYPE_MY_PROFILE = 0
        const val VIEW_TYPE_FRIEND_PROFILE = 1
    }
}