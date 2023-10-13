package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptDataSource
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.showToast

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
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

        binding.lifecycleOwner = this

        initLayout()
        addListeners()
        finishOnDoubleBackPress()
    }

    private fun initLayout() {
        val doSoptDataSource = DoSoptDataSource(this)
        with(binding) {
            tvMainNickname.text = doSoptDataSource.userNickname
            tvMainId.text = doSoptDataSource.userId
            tvMainMbti.text = doSoptDataSource.userMBTI
        }
    }

    private fun addListeners() {
        binding.tvMainLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        LogoutDialog().show(supportFragmentManager, DIALOG)
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