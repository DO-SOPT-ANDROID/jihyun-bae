package org.sopt.dosopttemplate.presentation.home

import android.content.Intent
import android.content.res.Configuration
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
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.profileDetail.ProfileDetailActivity
import org.sopt.dosopttemplate.presentation.type.ScrollableView
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingDoSoptDialogFragment
import org.sopt.dosopttemplate.util.binding.BindingFragment
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home), ScrollableView {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var portraitHomeProfileAdapter: PortraitHomeProfileAdapter
    private lateinit var landscapeHomeProfileAdapter: LandscapeHomeProfileAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        initLayout()
        addListeners()
        initAdapter()
        collectData()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            with(binding) {
                rvHome.visibility = View.INVISIBLE
                vpHome.visibility = View.VISIBLE
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            with(binding) {
                rvHome.visibility = View.VISIBLE
                vpHome.visibility = View.INVISIBLE
            }
        }
    }

    override fun scrollToTop() {
        binding.rvHome.smoothScrollToPosition(FIRST_POSITION)
    }

    private fun initLayout() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            with(binding) {
                rvHome.visibility = View.INVISIBLE
                vpHome.visibility = View.VISIBLE
            }
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            with(binding) {
                rvHome.visibility = View.VISIBLE
                vpHome.visibility = View.INVISIBLE
            }
        }

        viewModel.getProfileList()
    }

    private fun addListeners() {
        binding.fabHomeAddFriend.setOnClickListener {
            showAddFriendDialog()
        }
    }

    private fun initAdapter() {
        runCatching {
            portraitHomeProfileAdapter = PortraitHomeProfileAdapter(::moveToProfileDetail,
                { profile -> showDeleteProfileDialog(profile) })
            binding.rvHome.adapter = portraitHomeProfileAdapter
        }.onFailure { throwable -> Timber.e(throwable.message) }

        runCatching {
            landscapeHomeProfileAdapter = LandscapeHomeProfileAdapter(::moveToProfileDetail,
                { profile -> showDeleteProfileDialog(profile) })
            binding.vpHome.adapter = landscapeHomeProfileAdapter
        }.onFailure { throwable -> Timber.e(throwable.message) }
    }

    private fun collectData() {
        viewModel.profileListState.flowWithLifecycle(lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    portraitHomeProfileAdapter.submitList(uiState.data)
                    landscapeHomeProfileAdapter.submitList(uiState.data)
                }

                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun moveToProfileDetail(profile: Profile) {
        Intent(requireContext(), ProfileDetailActivity::class.java).apply {
            putExtra(
                PROFILE, when (profile) {
                    is Profile.MyProfile -> profile.toParcelizeMyProfile()
                    is Profile.FriendProfile -> profile.toParcelizeFriendProfile()
                    is Profile.FriendProfileWithMusic -> profile.toParcelizeFriendProfileWithMusic()
                    is Profile.FriendProfileWithBirth -> profile.toParcelizeFriendProfileWithBirth()
                }
            )
            startActivity(this)
        }
    }

    private fun showAddFriendDialog() {
        AddFriendDialog(
            onDialogClosed = { viewModel.getProfileList() },
            clickAddFriendBtn = { name ->
                viewModel.getProfileList()
                viewModel.insertProfile(name)
            }).show(childFragmentManager, ADD_FRIEND_DIALOG)
    }

    private fun showDeleteProfileDialog(profile: Profile) {
        BindingDoSoptDialogFragment(
            icon = R.drawable.ic_sad_24,
            title = getString(R.string.delete_friend),
            context = getString(R.string.delete_friend_context),
            leftBtnText = getString(R.string.dialog_yes),
            rightBtnText = getString(R.string.dialog_no),
            clickLeftBtn = { viewModel.deleteProfile(profile) },
            clickRightBtn = {},
            onDialogClosed = { viewModel.getProfileList() }
        ).show(childFragmentManager, DELETE_PROFILE_DIALOG)
    }

    companion object {
        const val FIRST_POSITION = 0
        const val PROFILE = "profile"
        const val ADD_FRIEND_DIALOG = "addFriendDialog"
        const val DELETE_PROFILE_DIALOG = "deleteProfileDialog"
    }
}