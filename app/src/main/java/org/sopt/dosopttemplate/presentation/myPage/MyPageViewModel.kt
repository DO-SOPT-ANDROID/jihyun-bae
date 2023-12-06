package org.sopt.dosopttemplate.presentation.myPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.UserInfo
import org.sopt.dosopttemplate.domain.usecase.ClearUserDataSourceUseCase
import org.sopt.dosopttemplate.domain.usecase.GetUserIdUseCase
import org.sopt.dosopttemplate.domain.usecase.GetUserInfoUseCase
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val clearUserDataSourceUseCase: ClearUserDataSourceUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {
    private val _memberId = getUserIdUseCase()
    private val _getUserInfoState = MutableSharedFlow<UiState<UserInfo>>()
    val getUserInfoState = _getUserInfoState.asSharedFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            _getUserInfoState.emit(UiState.Loading)
            runCatching {
                getUserInfoUseCase(_memberId).collect() { userInfo ->
                    _getUserInfoState.emit(UiState.Success(userInfo))
                }
            }.onFailure { exception: Throwable ->
                _getUserInfoState.emit(UiState.Error(exception.message))
            }
        }
    }

    fun clearUserDataSource() = clearUserDataSourceUseCase()
}