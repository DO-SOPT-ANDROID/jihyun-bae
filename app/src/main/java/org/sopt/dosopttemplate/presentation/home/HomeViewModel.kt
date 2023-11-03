package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.domain.repository.ProfileRoomRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileRoomRepository: ProfileRoomRepository
) : ViewModel() {
    private val _profileListState = MutableStateFlow<UiState<List<Profile>>>(UiState.Loading)
    val profileListState = _profileListState.asStateFlow()

    fun getProfileList() {
        viewModelScope.launch {
            profileRoomRepository.getProfileList()
                .onSuccess { profileList ->
                    _profileListState.value = UiState.Success(profileList)
                }
                .onFailure { throwable ->
                    _profileListState.value = UiState.Error(throwable.message)
                }
        }
    }
}