package org.sopt.dosopttemplate.presentation

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.presentation.doAndroid.DoAndroidFragment
import org.sopt.dosopttemplate.presentation.home.HomeFragment
import org.sopt.dosopttemplate.presentation.myPage.MyPageFragment
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.showToast

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
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

        initLayout()
        finishOnDoubleBackPress()
    }

    private fun initLayout() {
        navigateTo<HomeFragment>()

        binding.bnvHome.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_do_android -> navigateTo<DoAndroidFragment>()
                R.id.menu_home -> navigateTo<HomeFragment>()
                R.id.menu_my_page -> navigateTo<MyPageFragment>()
            }
            true
        }
    }

    private fun finishOnDoubleBackPress() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.fcv_home_container, T::class.java.canonicalName)
        }
    }

    companion object {
        const val INIT_BACK_PRESSED_TIME = 0L
        const val DELAY_TIME = 2000
    }
}