package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptDataSource
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.util.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this

        initLayout()
        addListeners()
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

    private fun showLogoutDialog(){
        LogoutDialog().show(supportFragmentManager, DIALOG)
    }

    companion object {
        const val DIALOG = "dialog"
    }
}