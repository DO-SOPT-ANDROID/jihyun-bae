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

    fun insertProfile(name: String) {
        viewModelScope.launch {
            profileRoomRepository.insertProfile(
                Profile.FriendProfile(
                    id = INITIAL_ID,
                    name = name,
                    profileImage = DEFAULT_PROFILE_IMAGE,
                    description = null
                )
            )
        }
    }

    fun deleteProfile(profile: Profile) {
        viewModelScope.launch {
            profileRoomRepository.deleteProfile(profile)
        }
    }

    companion object {
        const val INITIAL_ID = 0
        const val DEFAULT_PROFILE_IMAGE =
            "https://github.com/DO-SOPT-ANDROID/jihyun-bae/assets/103172971/4d714e80-37c3-45a6-9a07-c6b672b59dbe"
    }
}