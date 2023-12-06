package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.presentation.type.ScrollableView
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home),
    ScrollableView {
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = homeViewModel

        initAdapter()
        collectData()
    }

    override fun onDestroyView() {
        binding.rvFriend.adapter = null
        super.onDestroyView()
    }

    override fun scrollToTop() {
        binding.rvFriend.smoothScrollToPosition(FIRST_POSITION)
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter()
        binding.rvFriend.adapter = homeAdapter
    }

    private fun collectData() {
        homeViewModel.listUserState.flowWithLifecycle(lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Success -> homeAdapter.submitList(uiState.data)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
    }

    companion object {
        const val FIRST_POSITION = 0
    }
}