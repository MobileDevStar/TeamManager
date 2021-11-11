package com.itdev.teammanager.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.itdev.teammanager.R
import com.itdev.teammanager.adapters.MemberAdapter
import com.itdev.teammanager.databinding.FragmentMemberListBinding
import com.itdev.teammanager.viewmodels.MemberListViewModel
import dagger.hilt.android.AndroidEntryPoint



/**
 * A simple [Fragment] subclass.
 * Use the [MemberListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MemberListFragment : Fragment() {

    private val viewModel: MemberListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMemberListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MemberAdapter()
        binding.memberList.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_member_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_member_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: MemberAdapter) {
        viewModel.members.observe(viewLifecycleOwner) { members ->
            adapter.submitList(members)
        }
    }

    private fun updateData() {
        with(viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }
    }
}