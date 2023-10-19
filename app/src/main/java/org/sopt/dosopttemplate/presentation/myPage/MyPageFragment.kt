package org.sopt.dosopttemplate.presentation.myPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel by viewModels<MyPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val userInfo = viewModel.getUserInfo()
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
        LogoutDialog({ viewModel.clearUserDataSource() }).show(childFragmentManager, DIALOG)
    }

    companion object {
        const val DIALOG = "dialog"
    }
}