package org.sopt.dosopttemplate.presentation.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.dosopttemplate.databinding.ItemFriendProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.util.ItemDiffCallback

class LandscapeFriendProfileAdapter(
    private val moveToProfileDetail: (Profile) -> Unit,
    private val showDeleteProfileDialog: (Profile) -> Unit
) : ListAdapter<Profile, ProfileViewHolder>(
    ItemDiffCallback<Profile>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.id == new.id }
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileViewHolder {
        val binding =
            ItemFriendProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(
            binding,
            parent.context,
            moveToProfileDetail,
            showDeleteProfileDialog
        )
    }

    override fun onBindViewHolder(
        holder: ProfileViewHolder,
        position: Int
    ) {
        holder.onBind(currentList[position])
    }
}