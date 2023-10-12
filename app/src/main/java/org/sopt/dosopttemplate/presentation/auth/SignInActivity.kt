package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignInBinding
import org.sopt.dosopttemplate.presentation.auth.SignUpActivity.Companion.USER_INFO
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.model.UserInfo
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extension.getCompatibleParcelableExtra
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.showSnackbar
import org.sopt.dosopttemplate.util.extension.showToast

class SignInActivity : BindingActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setActivityResultLauncher()
        addListeners()
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
                moveToMain()
            } else {
                showToast(getString(R.string.sign_in_failed))
            }
        }

        binding.tvSignInSignUp.setOnClickListener {
            moveToSignUp()
        }
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(USER_INFO, viewModel.userInfo)
        startActivity(intent)
        finish()
    }

    private fun moveToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        resultLauncher.launch(intent)
    }
}