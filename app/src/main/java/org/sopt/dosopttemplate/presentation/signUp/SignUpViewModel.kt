package org.sopt.dosopttemplate.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    val id = MutableStateFlow("")
    val password = MutableStateFlow("")
    val nickname = MutableStateFlow("")
    val mbti = MutableStateFlow("")
    private val _signUpEnabled = MutableStateFlow(false)
    val signUpEnabled = _signUpEnabled.asStateFlow()
    private val _signUpState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val signUpState = _signUpState.asStateFlow()

    fun setSignUpValid() {
        _signUpEnabled.value =
            isIdValid() && isPasswordValid() && isNicknameValid() && isMBTIValid()
    }

    fun isIdValid(): Boolean = id.value.matches(ID_REGEX)

    fun isPasswordValid(): Boolean = password.value.matches(PASSWORD_REGEX)

    fun isNicknameValid(): Boolean = nickname.value.isNotBlank()

    fun isMBTIValid(): Boolean = mbti.value.matches(MBTI_REGEX)

    fun singUp() {
        viewModelScope.launch {
            _signUpState.value = UiState.Loading
            authRepository.signUp(
                username = id.value,
                nickname = nickname.value,
                password = password.value
            )
                .onSuccess {
                    _signUpState.value = UiState.Success(Unit)
                }
                .onFailure { exception: Throwable ->
                    _signUpState.value = UiState.Error(exception.message)
                }
        }
    }

    companion object {
        private const val ID_PATTERN = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}"
        private const val PASSWORD_PATTERN =
            "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}"
        private const val MBTI_PATTERN = "^[EI][NS][FT][JP]\$"
        private val ID_REGEX = Regex(ID_PATTERN)
        private val PASSWORD_REGEX = Regex(PASSWORD_PATTERN)
        private val MBTI_REGEX = Regex(MBTI_PATTERN)
    }
}