package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.repository.DummyRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dummyRepository: DummyRepository,
) : ViewModel() {
    fun getMockProfileList() = dummyRepository.getMockProfileList()
}