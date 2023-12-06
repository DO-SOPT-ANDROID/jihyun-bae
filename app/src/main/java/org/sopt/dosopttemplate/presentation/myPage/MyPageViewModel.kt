package org.sopt.dosopttemplate.presentation.myPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.UserInfo
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.domain.repository.UserRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _memberId = userRepository.getUserId()
    private val _getUserInfoState = MutableSharedFlow<UiState<UserInfo>>()
    val getUserInfoState = _getUserInfoState.asSharedFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            _getUserInfoState.emit(UiState.Loading)
            runCatching {
                authRepository.getUserInfo(_memberId).collect() { userInfo ->
                    _getUserInfoState.emit(UiState.Success(userInfo))
                }
            }.onFailure { exception: Throwable ->
                _getUserInfoState.emit(UiState.Error(exception.message))
            }
        }
    }

    fun clearUserDataSource() = userRepository.clearUserDataSource()
}