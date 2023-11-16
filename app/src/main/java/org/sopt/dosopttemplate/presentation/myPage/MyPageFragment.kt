package org.sopt.dosopttemplate.presentation.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.FIRST_POSITION
import org.sopt.dosopttemplate.presentation.signIn.SignInActivity
import org.sopt.dosopttemplate.presentation.type.ScrollableView
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingDoSoptDialogFragment
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page),
    ScrollableView {
    private val myPageViewModel by viewModels<MyPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = myPageViewModel

        addListeners()
        collectData()
    }

    override fun scrollToTop() {
        binding.svMyPage.smoothScrollTo(FIRST_POSITION, FIRST_POSITION)
    }

    private fun addListeners() {
        binding.tvMyPageLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun collectData() {
        myPageViewModel.getUserInfoState.flowWithLifecycle(lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    with(binding) {
                        tvMyPageNickname.text = uiState.data.nickname
                        tvMyPageId.text = uiState.data.username
                    }
                }

                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun showLogoutDialog() {
        BindingDoSoptDialogFragment(
            icon = R.drawable.ic_sad_24,
            title = getString(R.string.logout),
            context = getString(R.string.logout_dialog_context),
            leftBtnText = getString(R.string.dialog_yes),
            rightBtnText = getString(R.string.dialog_no),
            clickLeftBtn = { clickLogoutDialogLeftBtn() },
            clickRightBtn = {},
            onDialogClosed = {}
        ).show(childFragmentManager, LOGOUT_DIALOG)
    }

    private fun clickLogoutDialogLeftBtn() {
        myPageViewModel.clearUserDataSource()
        Intent(requireContext(), SignInActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }

    companion object {
        const val LOGOUT_DIALOG = "logoutDialog"
    }
}