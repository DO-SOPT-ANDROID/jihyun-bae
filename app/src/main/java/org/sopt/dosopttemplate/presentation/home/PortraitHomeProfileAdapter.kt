package org.sopt.dosopttemplate.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemHomeFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemHomeMyProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.type.ProfileInfoType
import org.sopt.dosopttemplate.util.ItemDiffCallback

class PortraitHomeProfileAdapter(
    private val moveToProfileDetail: (Profile) -> Unit
) : ListAdapter<Profile, RecyclerView.ViewHolder>(
    ItemDiffCallback<Profile>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new }
    )
) {
    class MyProfileViewHolder(
        private val binding: ItemHomeMyProfileBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(myProfile: Profile.MyProfile) {
            binding.run {
                ivMyProfile.setImageResource(myProfile.profileImage)
                tvMyProfileName.text = myProfile.name
                tvMyProfileDescription.text = myProfile.description
                if (myProfile.description.isNullOrEmpty()) tvMyProfileDescription.visibility =
                    View.GONE

                with(includeMyProfileInfo) {
                    layoutPortraitProfileInfo.setBackgroundResource(ProfileInfoType.DESCRIPTION.backgroundRes)
                    ProfileInfoType.DESCRIPTION.contextRes?.let { tvPortraitProfileInfoContext.setText(it) }
                    tvPortraitProfileInfoIcon.setImageResource(ProfileInfoType.DESCRIPTION.iconRes)
                }
            }
        }
    }

    class FriendProfileViewHolder(
        private val binding: ItemHomeFriendProfileBinding,
        private val moveToProfileDetail: (Profile) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(friendProfile: Profile.FriendProfile) {
            with(binding) {
                ivFriendProfile.setImageResource(friendProfile.profileImage)
                tvFriendProfileName.text = friendProfile.name
                tvFriendProfileDescription.text = friendProfile.description
                if (friendProfile.description.isNullOrEmpty()) tvFriendProfileDescription.visibility =
                    View.GONE

                root.setOnClickListener {
                    moveToProfileDetail(friendProfile)
                }
            }
        }
    }

    class FriendProfileWithMusicViewHolder(
        private val binding: ItemHomeFriendProfileBinding,
        private val context: Context,
        private val moveToProfileDetail: (Profile) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(friendProfileWithMusic: Profile.FriendProfileWithMusic) {
            binding.run {
                ivFriendProfile.setImageResource(friendProfileWithMusic.profileImage)
                tvFriendProfileName.text = friendProfileWithMusic.name
                tvFriendProfileDescription.text = friendProfileWithMusic.description
                if (friendProfileWithMusic.description.isNullOrEmpty()) tvFriendProfileDescription.visibility =
                    View.GONE

                with(includeFriendProfileInfo) {
                    layoutPortraitProfileInfo.setBackgroundResource(ProfileInfoType.MUSIC.backgroundRes)
                    tvPortraitProfileInfoContext.text = context.getString(
                        R.string.home_music,
                        friendProfileWithMusic.musicTitle,
                        friendProfileWithMusic.singer
                    )
                    tvPortraitProfileInfoIcon.setImageResource(ProfileInfoType.MUSIC.iconRes)
                }

                root.setOnClickListener {
                    moveToProfileDetail(friendProfileWithMusic)
                }
            }
        }
    }

    class FriendProfileWithBirthViewHolder(
        private val binding: ItemHomeFriendProfileBinding,
        private val context: Context,
        private val moveToProfileDetail: (Profile) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(friendProfileWithBirth: Profile.FriendProfileWithBirth) {
            binding.run {
                ivFriendProfile.setImageResource(friendProfileWithBirth.profileImage)
                tvFriendProfileName.text = friendProfileWithBirth.name
                tvFriendProfileDescription.text = context.getString(
                    R.string.home_birth,
                    friendProfileWithBirth.birthMonth,
                    friendProfileWithBirth.birthDay
                )
                ivFriendProfileBirth.visibility = View.VISIBLE

                with(includeFriendProfileInfo) {
                    layoutPortraitProfileInfo.setBackgroundResource(ProfileInfoType.BIRTH.backgroundRes)
                    ProfileInfoType.BIRTH.contextRes?.let { tvPortraitProfileInfoContext.setText(it) }
                    tvPortraitProfileInfoIcon.setImageResource(ProfileInfoType.BIRTH.iconRes)
                }

                root.setOnClickListener {
                    moveToProfileDetail(friendProfileWithBirth)
                }
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
                moveToProfileDetail
            )
        }

        VIEW_TYPE_FRIEND_PROFILE_WITH_MUSIC -> {
            FriendProfileWithMusicViewHolder(
                ItemHomeFriendProfileBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                ),
                parent.context,
                moveToProfileDetail
            )
        }

        VIEW_TYPE_FRIEND_PROFILE_WITH_BIRTH -> {
            FriendProfileWithBirthViewHolder(
                ItemHomeFriendProfileBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                ),
                parent.context,
                moveToProfileDetail
            )
        }

        else -> throw Exception("unknown type!!")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyProfileViewHolder -> holder.onBind(currentList[position] as Profile.MyProfile)
            is FriendProfileViewHolder -> holder.onBind(currentList[position] as Profile.FriendProfile)
            is FriendProfileWithMusicViewHolder -> holder.onBind(currentList[position] as Profile.FriendProfileWithMusic)
            is FriendProfileWithBirthViewHolder -> holder.onBind(currentList[position] as Profile.FriendProfileWithBirth)
        }
    }

    override fun getItemViewType(position: Int): Int = when (currentList[position]) {
        is Profile.MyProfile -> VIEW_TYPE_MY_PROFILE
        is Profile.FriendProfile -> VIEW_TYPE_FRIEND_PROFILE
        is Profile.FriendProfileWithMusic -> VIEW_TYPE_FRIEND_PROFILE_WITH_MUSIC
        is Profile.FriendProfileWithBirth -> VIEW_TYPE_FRIEND_PROFILE_WITH_BIRTH
    }

    companion object {
        const val VIEW_TYPE_MY_PROFILE = 0
        const val VIEW_TYPE_FRIEND_PROFILE = 1
        const val VIEW_TYPE_FRIEND_PROFILE_WITH_MUSIC = 2
        const val VIEW_TYPE_FRIEND_PROFILE_WITH_BIRTH = 3
    }
}