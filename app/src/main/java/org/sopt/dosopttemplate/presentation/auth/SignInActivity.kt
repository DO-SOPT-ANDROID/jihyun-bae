package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.datasource.local.DoSoptDataSource
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.presentation.auth.SignUpActivity.Companion.USER_INFO
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.model.UserInfo
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.getCompatibleParcelableExtra
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar
import org.sopt.dosopttemplate.util.extension.showToast

@AndroidEntryPoint
class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setActivityResultLauncher()
        addListeners()
        setAutoLogin()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard(currentFocus ?: View(this))
        return super.dispatchTouchEvent(ev)
    }

    private fun setActivityResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                if (activityResult.resultCode == RESULT_OK) {
                    viewModel.userInfo =
                        activityResult.data?.getCompatibleParcelableExtra<UserInfo>(USER_INFO)
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
                viewModel.saveAutoLoginInfo()
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
        val doSoptDataSource = DoSoptDataSource(this)
        if (doSoptDataSource.isLogin) {
            showToast(getString(R.string.sign_in_auto_login_success))
            moveToMain()
        }
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        resultLauncher.launch(intent)
    }
}