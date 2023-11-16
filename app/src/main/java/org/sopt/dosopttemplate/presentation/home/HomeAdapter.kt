package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.dosopttemplate.databinding.ItemHomeUserBinding
import org.sopt.dosopttemplate.domain.model.ReqresUser
import org.sopt.dosopttemplate.util.ItemDiffCallback

class HomeAdapter() : ListAdapter<ReqresUser, HomeViewHolder>(
    ItemDiffCallback<ReqresUser>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.id == new.id }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemHomeUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(
            binding,
            parent.context
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}