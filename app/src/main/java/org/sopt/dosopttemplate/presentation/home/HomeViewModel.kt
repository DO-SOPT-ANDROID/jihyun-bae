package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.ReqresUser
import org.sopt.dosopttemplate.domain.usecase.GetListUsersUseCase
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListUsersUseCase: GetListUsersUseCase
) : ViewModel() {
    private val _listUserState = MutableStateFlow<UiState<List<ReqresUser>>>(UiState.Empty)
    val listUserState = _listUserState.asStateFlow()

    init {
        getListUsers(PAGE)
    }

    private fun getListUsers(page: Int) {
        viewModelScope.launch {
            _listUserState.value = UiState.Loading
            runCatching {
                getListUsersUseCase(page).collect() { userList ->
                    _listUserState.value = UiState.Success(userList)
                }
            }.onFailure { exception: Throwable ->
                _listUserState.value = UiState.Error(exception.message)
            }
        }
    }

    companion object {
        const val PAGE = 2
    }
}