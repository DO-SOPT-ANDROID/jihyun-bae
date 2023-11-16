package org.sopt.dosopttemplate.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.UserData
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.domain.repository.UserRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    val id = MutableStateFlow("")
    val password = MutableStateFlow("")
    private val _signInState = MutableStateFlow<UiState<UserData>>(UiState.Empty)
    val signInState = _signInState.asStateFlow()

    fun signIn() {
        viewModelScope.launch {
            _signInState.value = UiState.Loading
            authRepository.signIn(
                username = id.value,
                password = password.value
            )
                .onSuccess { userData ->
                    _signInState.value = UiState.Success(userData)
                }
                .onFailure { exception: Throwable ->
                    _signInState.value = UiState.Error(exception.message)
                }
        }
    }

    fun setAutoLogin(id: Int) {
        with(userRepository) {
            setAutoLogin(true)
            setUserId(id)
        }
    }

    fun getAutoLogin() = userRepository.getAutoLogin()
}