package org.sopt.dosopttemplate.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.UserData
import org.sopt.dosopttemplate.domain.usecase.GetAutoLoginUseCase
import org.sopt.dosopttemplate.domain.usecase.SetAutoLoginUseCase
import org.sopt.dosopttemplate.domain.usecase.SetUserIdUseCase
import org.sopt.dosopttemplate.domain.usecase.SignInUseCase
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val setAuthLoginUseCase: SetAutoLoginUseCase,
    private val setUserIdUseCase: SetUserIdUseCase,
    private val getAuthLoginUseCase: GetAutoLoginUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    val id = MutableStateFlow("")
    val password = MutableStateFlow("")
    private val _signInState = MutableSharedFlow<UiState<UserData>>()
    val signInState = _signInState.asSharedFlow()

    fun signIn() {
        viewModelScope.launch {
            _signInState.emit(UiState.Loading)
            runCatching {
                signInUseCase(
                    username = id.value,
                    password = password.value
                ).collect() { userData ->
                    _signInState.emit(UiState.Success(userData))
                }
            }.onFailure { exception: Throwable ->
                _signInState.emit(UiState.Error(exception.message))
            }
        }
    }

    fun setAutoLogin(id: Int) {
        setAuthLoginUseCase(true)
        setUserIdUseCase(id)
    }

    fun getAutoLogin() = getAuthLoginUseCase()
}