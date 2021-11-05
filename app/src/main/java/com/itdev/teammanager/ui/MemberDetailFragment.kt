package com.itdev.teammanager.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.itdev.teammanager.R
import com.itdev.teammanager.data.model.Member
import com.itdev.teammanager.databinding.FragmentMemberDetailBinding
import com.itdev.teammanager.viewmodels.MemberDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a single Member detail screen.
 */
@AndroidEntryPoint
class MemberDetailFragment : Fragment() {

    private val args: MemberDetailFragmentArgs by navArgs()

    private val memberDetailViewModel: MemberDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("dddddddddddddddddddddddddd", args.memberId)

        val binding = DataBindingUtil.inflate<FragmentMemberDetailBinding>(
            inflater,
            R.layout.fragment_member_detail,
            container,
            false
        ).apply {
            viewModel = memberDetailViewModel
            lifecycleOwner = viewLifecycleOwner

            galleryNav.setOnClickListener { navigateToGallery() }

            var isToolbarShown = false

            // scroll change listener begins at Y = 0 when image is fully collapsed
            memberDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    // User scrolled past image to height of toolbar and the title text is
                    // underneath the toolbar, so the toolbar should be shown.
                    val shouldShowToolbar = scrollY > toolbar.height

                    // The new state of the toolbar differs from the previous state; update
                    // appbar and toolbar attributes.
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        // Use shadow animator to add elevation if toolbar is shown
                        appbar.isActivated = shouldShowToolbar

                        // Show the plant name if toolbar is shown
                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_share -> {
                        createShareIntent()
                        true
                    }
                    else -> false
                }
            }
        }


        setHasOptionsMenu(true)

        return binding.root
    }

    // Helper function for calling a share functionality.
    // Should be used when user presses a share button/menu item.
    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = memberDetailViewModel.member.value.let { member ->
            if (member == null) {
                ""
            } else {
                getString(R.string.share_text_member, member.name)
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
    }

    private fun navigateToGallery() {
        memberDetailViewModel.member.value?.let { member ->
            val direction =
                MemberDetailFragmentDirections.actionMemberDetailFragmentToGalleryFragment(member.name)
            findNavController().navigate(direction)
        }
    }

    fun interface Callback {
        fun add(member: Member?)
    }


}