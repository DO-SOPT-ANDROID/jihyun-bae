package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.ReqresUser
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reqresRepository: ReqresRepository
) : ViewModel() {
    private val _listUserState = MutableStateFlow<UiState<List<ReqresUser>>>(UiState.Empty)
    val listUserState = _listUserState.asStateFlow()

    init {
        getListUsers(PAGE)
    }

    fun getListUsers(page: Int) {
        viewModelScope.launch {
            reqresRepository.getListUsers(page)
                .onSuccess { listUser ->
                    _listUserState.value = UiState.Success(listUser)
                }
                .onFailure { exception: Throwable ->
                    _listUserState.value = UiState.Error(exception.message)
                }
        }
    }

    companion object {
        const val PAGE = 2
    }
}