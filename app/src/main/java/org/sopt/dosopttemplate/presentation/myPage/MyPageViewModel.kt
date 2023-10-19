package org.sopt.dosopttemplate.presentation.myPage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    fun getUserInfo() = userRepository.getUserInfo()
    fun clearUserDataSource() = userRepository.clearUserDataSource()
}