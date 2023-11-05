package org.sopt.dosopttemplate.presentation.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.presentation.auth.SignInActivity
import org.sopt.dosopttemplate.presentation.home.HomeFragment.Companion.FIRST_POSITION
import org.sopt.dosopttemplate.presentation.type.ScrollableView
import org.sopt.dosopttemplate.util.binding.BindingDoSoptDialogFragment
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page),
    ScrollableView {
    private val myPageViewModel by viewModels<MyPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = myPageViewModel

        initLayout()
        addListeners()
    }

    override fun scrollToTop() {
        binding.svMyPage.smoothScrollTo(FIRST_POSITION, FIRST_POSITION)
    }

    private fun initLayout() {
        val userInfo = myPageViewModel.getUserInfo()
        with(binding) {
            tvMyPageNickname.text = userInfo.nickname
            tvMyPageId.text = userInfo.id
            tvMyPageMbti.text = userInfo.mbti
        }
    }

    private fun addListeners() {
        binding.tvMyPageLogout.setOnClickListener {
            showLogoutDialog()
        }
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