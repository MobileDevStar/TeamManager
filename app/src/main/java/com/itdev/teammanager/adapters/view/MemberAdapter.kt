package com.itdev.teammanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itdev.teammanager.data.model.Member
import com.itdev.teammanager.databinding.ListItemMemberBinding

/**
 * Adapter for the [RecyclerView] in [MemberListFragment].
 */
class MemberAdapter : ListAdapter<Member, RecyclerView.ViewHolder>(MemberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MemberViewHolder(
            ListItemMemberBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val member = getItem(position)
        (holder as MemberViewHolder).bind(member)
    }

    class MemberViewHolder(
        private val binding: ListItemMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.member?.let { member ->
                    navigateToMember(member, it)
                }
            }
        }

        private fun navigateToMember(
            member: Member,
            view: View
        ) {
//            val direction =
//                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
//                    plant.plantId
//                )
//            view.findNavController().navigate(direction)
        }

        fun bind(item: Member) {
            binding.apply {
                member = item
                executePendingBindings()
            }
        }
    }
}

private class MemberDiffCallback : DiffUtil.ItemCallback<Member>() {

    override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.memberId == newItem.memberId
    }

    override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem == newItem
    }
}