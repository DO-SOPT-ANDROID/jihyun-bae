package org.sopt.dosopttemplate.presentation.myPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val _getUserInfoState = MutableStateFlow<UiState<UserInfo>>(UiState.Empty)
    val getUserInfoState = _getUserInfoState.asStateFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            _getUserInfoState.value = UiState.Loading
            authRepository.getUserInfo(_memberId)
                .onSuccess { userInfo ->
                    _getUserInfoState.value = UiState.Success(userInfo)
                }
                .onFailure { exception: Throwable ->
                    _getUserInfoState.value = UiState.Error(exception.message)
                }
        }
    }

    fun clearUserDataSource() = userRepository.clearUserDataSource()
}