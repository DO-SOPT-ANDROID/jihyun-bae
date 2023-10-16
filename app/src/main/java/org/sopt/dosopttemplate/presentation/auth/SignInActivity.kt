package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.presentation.auth.SignUpActivity.Companion.USER_INFO
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.model.User
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.getCompatibleParcelableExtra
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar
import org.sopt.dosopttemplate.util.extension.showToast

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var backPressedTime = MainActivity.INIT_BACK_PRESSED_TIME
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - backPressedTime <= MainActivity.DELAY_TIME) {
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

        setActivityResultLauncher()
        addListeners()
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
                    viewModel.user =
                        activityResult.data?.getCompatibleParcelableExtra<User>(USER_INFO)?.toUser()
                            ?: return@registerForActivityResult
                    binding.root.showSnackbar(getString(R.string.sign_up_success))
                }
            }
    }

    private fun addListeners() {
        binding.btnSignInSignIn.setOnClickListener {
            if (viewModel.isSignInEnabled(
                    binding.etSignInId.text.toString(),
                    binding.etSignInPassword.text.toString()
                )
            ) {
                showToast(getString(R.string.sign_in_success))
                viewModel.setAutoLogin()
                moveToMain()
            } else {
                showToast(getString(R.string.sign_in_failed))
            }
        }

        binding.tvSignInSignUp.setOnClickListener {
            moveToSignUp()
        }
    }

    private fun setAutoLogin() {
        if (viewModel.getAutoLogin()) {
            showToast(getString(R.string.sign_in_auto_login_success))
            moveToMain()
        }
    }

    private fun finishOnDoubleBackPress() {
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun moveToMain() {
        Intent(this@SignInActivity, MainActivity::class.java).apply {
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