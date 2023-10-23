package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var homeProfileAdapter: HomeProfileAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        initLayout()
    }

    private fun initLayout() {
        homeProfileAdapter = HomeProfileAdapter()
        homeProfileAdapter.submitList(viewModel.getMockProfileList())
        binding.rvHome.adapter = homeProfileAdapter
    }
}