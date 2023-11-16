package org.sopt.dosopttemplate.presentation.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemFriendMyProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.util.ItemDiffCallback

class PortraitFriendProfileAdapter(
    private val moveToProfileDetail: (Profile) -> Unit,
    private val showDeleteProfileDialog: (Profile) -> Unit
) : ListAdapter<Profile, RecyclerView.ViewHolder>(
    ItemDiffCallback<Profile>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.id == new.id }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        VIEW_TYPE_MY_PROFILE -> {
            MyProfileViewHolder(
                ItemFriendMyProfileBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        VIEW_TYPE_FRIEND_PROFILE -> {
            FriendProfileViewHolder(
                ItemFriendFriendProfileBinding.inflate(
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