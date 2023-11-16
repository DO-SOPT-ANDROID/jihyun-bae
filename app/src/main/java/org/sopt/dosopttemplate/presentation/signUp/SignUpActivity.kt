package org.sopt.dosopttemplate.presentation.signUp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.presentation.signIn.SignInActivity
import org.sopt.dosopttemplate.presentation.type.SignUpErrorType
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val signUpViewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = signUpViewModel

        addListeners()
        collectData()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard(currentFocus ?: View(this))
        return super.dispatchTouchEvent(ev)
    }

    private fun addListeners() {
        binding.btnSignUpSignUp.setOnClickListener {
            signUpViewModel.singUp()
        }
    }

    private fun collectData() {
        signUpViewModel.id.flowWithLifecycle(lifecycle).onEach { id ->
            binding.layoutSignUpId.error =
                id.takeIf { it.isNotEmpty() }?.takeIf { !signUpViewModel.isIdValid() }
                    ?.let { getString(SignUpErrorType.ID.errorMessage) }
            signUpViewModel.setSignUpValid()
        }.launchIn(lifecycleScope)

        signUpViewModel.password.flowWithLifecycle(lifecycle).onEach { password ->
            binding.layoutSignUpPassword.error =
                password.takeIf { it.isNotEmpty() }?.takeIf { !signUpViewModel.isPasswordValid() }
                    ?.let { getString(SignUpErrorType.PASSWORD.errorMessage) }
            signUpViewModel.setSignUpValid()
        }.launchIn(lifecycleScope)

        signUpViewModel.nickname.flowWithLifecycle(lifecycle).onEach { nickname ->
            binding.layoutSignUpNickname.error =
                nickname.takeIf { it.isNotEmpty() }?.takeIf { !signUpViewModel.isNicknameValid() }
                    ?.let { getString(SignUpErrorType.NICKNAME.errorMessage) }
            signUpViewModel.setSignUpValid()
        }.launchIn(lifecycleScope)

        signUpViewModel.mbti.flowWithLifecycle(lifecycle).onEach { mbti ->
            binding.layoutSignUpMbti.error =
                mbti.takeIf { it.isNotEmpty() }?.takeIf { !signUpViewModel.isMBTIValid() }
                    ?.let { getString(SignUpErrorType.MBTI.errorMessage) }
            signUpViewModel.setSignUpValid()
        }.launchIn(lifecycleScope)

        signUpViewModel.signUpEnabled.flowWithLifecycle(lifecycle).onEach { signUpEnabled ->
            if (signUpEnabled) {
                with(binding.btnSignUpSignUp) {
                    isEnabled = true
                    backgroundTintList = ContextCompat.getColorStateList(context, R.color.main_2)
                    setTextColor(getColor(R.color.gray_100))
                }
            } else {
                with(binding.btnSignUpSignUp) {
                    isEnabled = false
                    backgroundTintList = ContextCompat.getColorStateList(context, R.color.gray_200)
                    setTextColor(getColor(R.color.gray_400))
                }
            }
        }.launchIn(lifecycleScope)

        signUpViewModel.signUpState.flowWithLifecycle(lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Success -> moveToSignIn()
                is UiState.Error -> binding.root.showSnackbar(getString(R.string.sign_up_failed))
                else -> Unit
            }

        }.launchIn(lifecycleScope)
    }

    private fun moveToSignIn() {
        Intent(this@SignUpActivity, SignInActivity::class.java).apply {
            setResult(RESULT_OK, this)
            finish()
        }
    }
}