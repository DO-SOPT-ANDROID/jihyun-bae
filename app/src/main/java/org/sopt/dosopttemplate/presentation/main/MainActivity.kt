package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.auth.SignUpActivity.Companion.USER_INFO
import org.sopt.dosopttemplate.presentation.model.UserInfo
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.getCompatibleParcelableExtra

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this

        initLayout()
    }

    private fun initLayout() {
        intent.getCompatibleParcelableExtra<UserInfo>(USER_INFO)?.let { userInfo ->
            with(binding) {
                tvMainNickname.text = userInfo.nickname
                tvMainId.text = userInfo.id
                tvMainMbti.text = userInfo.mbti
            }
        }
    }
}