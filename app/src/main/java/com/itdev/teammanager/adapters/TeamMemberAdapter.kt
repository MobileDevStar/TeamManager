package com.itdev.teammanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itdev.teammanager.R
import com.itdev.teammanager.data.model.MemberAndTeamMembers
import com.itdev.teammanager.databinding.ListItemTeamMemberBinding
import com.itdev.teammanager.ui.HomeViewPagerFragmentDirections
import com.itdev.teammanager.ui.TeamFragment
import com.itdev.teammanager.viewmodels.TeamMemberItemViewModel

/**
 * Adapter for the [RecyclerView] in [TeamFragment].
 */
class TeamMemberAdapter : ListAdapter<MemberAndTeamMembers, TeamMemberAdapter.ViewHolder>(TeamMemberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_team_member,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemTeamMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.viewModel?.memberId?.let { memberId ->
                    navigateToMember(memberId, it)
                }
            }
        }

        private fun navigateToMember(
            memberId: String,
            view: View
        ) {
            val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToMemberDetailFragment(memberId)
            view.findNavController().navigate(direction)
        }

        fun bind(members: MemberAndTeamMembers) {
            with(binding) {
                viewModel = TeamMemberItemViewModel(members)
                executePendingBindings()
            }
        }
    }
}

private class TeamMemberDiffCallback : DiffUtil.ItemCallback<MemberAndTeamMembers>() {

    override fun areItemsTheSame(oldItem: MemberAndTeamMembers, newItem: MemberAndTeamMembers): Boolean {
        return oldItem.member.memberId == newItem.member.memberId
    }

    override fun areContentsTheSame(oldItem: MemberAndTeamMembers, newItem: MemberAndTeamMembers): Boolean {
        return oldItem.member == newItem.member
    }
}