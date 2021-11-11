package com.itdev.teammanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.itdev.teammanager.R
import com.itdev.teammanager.adapters.MEMBER_LIST_PAGE_INDEX
import com.itdev.teammanager.adapters.TeamMemberAdapter
import com.itdev.teammanager.databinding.FragmentTeamBinding
import com.itdev.teammanager.viewmodels.TeamMemberListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFragment : Fragment() {

    private lateinit var binding: FragmentTeamBinding

    private val viewModel: TeamMemberListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamBinding.inflate(inflater, container, false)

        val adapter = TeamMemberAdapter()
        binding.teamList.adapter = adapter

        binding.addMember.setOnClickListener{
            navigateToPlantListPage()
        }

        subscribeUi(adapter, binding)

        return binding.root
    }

    private fun subscribeUi(adapter: TeamMemberAdapter, binding: FragmentTeamBinding) {
        viewModel.teamMembers.observe(viewLifecycleOwner) { result ->
            binding.hasMember = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

    // TODO: convert to data binding if applicable
    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem =
            MEMBER_LIST_PAGE_INDEX
    }
}