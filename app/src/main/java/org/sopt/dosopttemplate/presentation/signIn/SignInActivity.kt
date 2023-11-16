package org.sopt.dosopttemplate.presentation.signIn

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.presentation.HomeActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar
import org.sopt.dosopttemplate.util.extension.showToast

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val signInViewModel by viewModels<SignInViewModel>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var backPressedTime = HomeActivity.INIT_BACK_PRESSED_TIME
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= HomeActivity.DELAY_TIME) {
                finish()
            } else {
                backPressedTime = System.currentTimeMillis()
                showToast(getString(R.string.double_back_press_message))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = signInViewModel

        setActivityResultLauncher()
        addListeners()
        collectData()
        setAutoLogin()
        finishOnDoubleBackPress()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard(currentFocus ?: View(this))
        return super.dispatchTouchEvent(ev)
    }

    private fun setActivityResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                if (activityResult.resultCode == RESULT_OK) {
                    binding.root.showSnackbar(getString(R.string.sign_up_success))
                }
            }
    }

    private fun addListeners() {
        binding.btnSignInSignIn.setOnClickListener {
            signInViewModel.signIn()
        }

        binding.tvSignInSignUp.setOnClickListener {
            moveToSignUp()
        }
    }

    private fun collectData() {
        signInViewModel.signInState.flowWithLifecycle(lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    showToast(getString(R.string.sign_in_success, uiState.data.id))
                    signInViewModel.setAutoLogin(id = uiState.data.id)
                    moveToMain()
                }

                is UiState.Error -> showToast(getString(R.string.sign_in_failed))
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    private fun setAutoLogin() {
        if (signInViewModel.getAutoLogin()) {
            showToast(getString(R.string.sign_in_auto_login_success))
            moveToMain()
        }
    }

    private fun finishOnDoubleBackPress() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun moveToMain() {
        Intent(this@SignInActivity, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
            finish()
        }
    }

    private fun moveToSignUp() {
        Intent(this@SignInActivity, SignUpActivity::class.java).apply {
            resultLauncher.launch(this)
        }
    }
}