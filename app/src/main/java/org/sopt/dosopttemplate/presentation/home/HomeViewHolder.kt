package org.sopt.dosopttemplate.presentation.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ItemHomeUserBinding
import org.sopt.dosopttemplate.domain.model.ReqresUser

class HomeViewHolder(
    private val binding: ItemHomeUserBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(reqresUser: ReqresUser) {
        with(binding) {
            ivHomeUserProfile.load(reqresUser.avatar)
            tvHomeProfileName.text =
                context.getString(R.string.home_name, reqresUser.firstName, reqresUser.lastName)
            tvHomeProfileEmail.text = reqresUser.email
        }
    }
}