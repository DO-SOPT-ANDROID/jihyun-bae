package org.sopt.dosopttemplate.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptDataSource
import org.sopt.dosopttemplate.databinding.DialogLogoutBinding
import org.sopt.dosopttemplate.presentation.auth.SignInActivity
import org.sopt.dosopttemplate.util.binding.BindingDialogFragment

class LogoutDialog : BindingDialogFragment<DialogLogoutBinding>(R.layout.dialog_logout) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this.viewLifecycleOwner

        addListeners()
    }

    private fun addListeners() {
        binding.btnLogoutDialogNo.setOnClickListener {
            dismiss()
        }

        binding.btnLogoutDialogYes.setOnClickListener {
            clearAutoLogin()
            moveToSignIn()
        }
    }

    private fun clearAutoLogin() {
        DoSoptDataSource(requireContext()).clear()
    }

    private fun moveToSignIn() {
        val intent = Intent(requireContext(), SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}