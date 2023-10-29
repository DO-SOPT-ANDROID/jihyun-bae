package org.sopt.dosopttemplate.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.profileDetail.ProfileDetailActivity
import org.sopt.dosopttemplate.presentation.type.ScrollableView
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home), ScrollableView {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var homeProfileAdapter: HomeProfileAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        initLayout()
    }

    override fun scrollToTop() {
        binding.rvHome.smoothScrollToPosition(FIRST_POSITION)
    }

    private fun initLayout() {
        homeProfileAdapter = HomeProfileAdapter(::moveToProfileDetail)
        homeProfileAdapter.submitList(viewModel.getMockProfileList())
        binding.rvHome.adapter = homeProfileAdapter
    }

    private fun moveToProfileDetail(profile: Profile) {
        Intent(requireContext(), ProfileDetailActivity::class.java).apply {
            putExtra(
                PROFILE, when (profile) {
                    is Profile.MyProfile -> profile.toMyProfile()
                    is Profile.FriendProfile -> profile.toFriendProfile()
                    is Profile.FriendProfileWithMusic -> profile.toFriendProfileWithMusic()
                    is Profile.FriendProfileWithBirth -> profile.toFriendProfileWithBirth()
                }
            )
            startActivity(this)
        }
    }

    companion object {
        const val FIRST_POSITION = 0
        const val PROFILE = "profile"
    }
}