package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.showToast

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    private var backPressedTime = INIT_BACK_PRESSED_TIME
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= DELAY_TIME) {
                finish()
            } else {
                backPressedTime = System.currentTimeMillis()
                showToast(getString(R.string.double_back_press_message))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        initLayout()
        addListeners()
        finishOnDoubleBackPress()
    }

    private fun initLayout() {
        val userInfo = viewModel.getUserInfo()
        with(binding) {
            tvMainNickname.text = userInfo.nickname
            tvMainId.text = userInfo.id
            tvMainMbti.text = userInfo.mbti
        }
    }

    private fun addListeners() {
        binding.tvMainLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        LogoutDialog({ viewModel.clearUserDataSource() }).show(supportFragmentManager, DIALOG)
    }

    private fun finishOnDoubleBackPress() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    companion object {
        const val DIALOG = "dialog"
        const val INIT_BACK_PRESSED_TIME = 0L
        const val DELAY_TIME = 2000
    }
}