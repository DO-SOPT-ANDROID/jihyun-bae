package org.sopt.dosopttemplate.presentation.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.DialogAddFriendBinding
import org.sopt.dosopttemplate.util.binding.BindingDialogFragment

class AddFriendDialog(
    private val onDialogClosed: () -> Unit,
    private val clickAddFriendBtn: (String) -> Unit
) : BindingDialogFragment<DialogAddFriendBinding>(R.layout.dialog_add_friend) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListeners()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDialogClosed.invoke()
    }

    private fun addListeners() {
        binding.btnAddFriend.setOnClickListener {
            if (!binding.etAddFriendName.text.isNullOrEmpty()) {
                clickAddFriendBtn.invoke(binding.etAddFriendName.text.toString())
                dismiss()
            }
        }
    }
}