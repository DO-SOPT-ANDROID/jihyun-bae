package org.sopt.dosopttemplate.presentation.friend

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
import org.sopt.dosopttemplate.databinding.FragmentFriendBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.presentation.profileDetail.ProfileDetailActivity
import org.sopt.dosopttemplate.presentation.type.ScrollableView
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BindingDoSoptDialogFragment
import org.sopt.dosopttemplate.util.binding.BindingFragment
import timber.log.Timber

@AndroidEntryPoint
class FriendFragment : BindingFragment<FragmentFriendBinding>(R.layout.fragment_friend),
    ScrollableView {
    private val friendViewModel by viewModels<FriendViewModel>()
    private lateinit var portraitFriendProfileAdapter: PortraitFriendProfileAdapter
    private lateinit var landscapeFriendProfileAdapter: LandscapeFriendProfileAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = friendViewModel

        initLayout()
        addListeners()
        initAdapter()
        collectData()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            with(binding) {
                rvFriend.visibility = View.INVISIBLE
                vpFriend.visibility = View.VISIBLE
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            with(binding) {
                rvFriend.visibility = View.VISIBLE
                vpFriend.visibility = View.INVISIBLE
            }
        }
    }

    override fun scrollToTop() {
        binding.rvFriend.smoothScrollToPosition(FIRST_POSITION)
    }

    private fun initLayout() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            with(binding) {
                rvFriend.visibility = View.INVISIBLE
                vpFriend.visibility = View.VISIBLE
            }
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            with(binding) {
                rvFriend.visibility = View.VISIBLE
                vpFriend.visibility = View.INVISIBLE
            }
        }

        friendViewModel.getProfileList()
    }

    private fun addListeners() {
        binding.fabFriendAddFriend.setOnClickListener {
            showAddFriendDialog()
        }
    }

    private fun initAdapter() {
        runCatching {
            portraitFriendProfileAdapter = PortraitFriendProfileAdapter(::moveToProfileDetail,
                { profile -> showDeleteProfileDialog(profile) })
            binding.rvFriend.adapter = portraitFriendProfileAdapter
        }.onFailure { throwable -> Timber.e(throwable.message) }

        runCatching {
            landscapeFriendProfileAdapter = LandscapeFriendProfileAdapter(::moveToProfileDetail,
                { profile -> showDeleteProfileDialog(profile) })
            binding.vpFriend.adapter = landscapeFriendProfileAdapter
        }.onFailure { throwable -> Timber.e(throwable.message) }
    }

    private fun collectData() {
        friendViewModel.profileListState.flowWithLifecycle(lifecycle).onEach { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    portraitFriendProfileAdapter.submitList(uiState.data)
                    landscapeFriendProfileAdapter.submitList(uiState.data)
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
            onDialogClosed = { friendViewModel.getProfileList() },
            clickAddFriendBtn = { name ->
                friendViewModel.getProfileList()
                friendViewModel.insertProfile(name)
            }).show(childFragmentManager, ADD_FRIEND_DIALOG)
    }

    private fun showDeleteProfileDialog(profile: Profile) {
        BindingDoSoptDialogFragment(
            icon = R.drawable.ic_sad_24,
            title = getString(R.string.delete_friend),
            context = getString(R.string.delete_friend_context),
            leftBtnText = getString(R.string.dialog_yes),
            rightBtnText = getString(R.string.dialog_no),
            clickLeftBtn = { friendViewModel.deleteProfile(profile) },
            clickRightBtn = {},
            onDialogClosed = { friendViewModel.getProfileList() }
        ).show(childFragmentManager, DELETE_PROFILE_DIALOG)
    }

    companion object {
        const val FIRST_POSITION = 0
        const val PROFILE = "profile"
        const val ADD_FRIEND_DIALOG = "addFriendDialog"
        const val DELETE_PROFILE_DIALOG = "deleteProfileDialog"
    }
}