package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.domain.model.User
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val authViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = authViewModel

        addListeners()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard(currentFocus ?: View(this))
        return super.dispatchTouchEvent(ev)
    }

    private fun addListeners() {
        binding.btnSignUpSignUp.setOnClickListener {
            val userInfo = with(binding) {
                User(
                    etSignUpId.text.toString(),
                    etSignUpPassword.text.toString(),
                    etSignUpNickname.text.toString(),
                    etSignUpMbti.text.toString()
                )
            }
            if (authViewModel.isSignUpEnabled(userInfo)) {
                moveToSignIn(userInfo.toParcelizeUser())
            } else {
                binding.root.showSnackbar(getString(R.string.sign_up_failed))
            }
        }
    }

    private fun moveToSignIn(userInfo: org.sopt.dosopttemplate.presentation.model.User) {
        Intent(this@SignUpActivity, SignInActivity::class.java).apply {
            putExtra(USER_INFO, userInfo)
            setResult(RESULT_OK, this)
            finish()
        }
    }

    companion object {
        const val USER_INFO = "USER_INFO"
    }
}