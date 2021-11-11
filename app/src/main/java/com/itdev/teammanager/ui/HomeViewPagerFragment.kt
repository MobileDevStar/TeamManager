package com.itdev.teammanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.itdev.teammanager.R
import com.itdev.teammanager.adapters.MEMBER_LIST_PAGE_INDEX
import com.itdev.teammanager.adapters.MY_TEAM_PAGE_INDEX
import com.itdev.teammanager.adapters.TeamManagerPagerAdapter
import com.itdev.teammanager.databinding.FragmentViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = TeamManagerPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_TEAM_PAGE_INDEX -> R.drawable.team_tab_selector
            MEMBER_LIST_PAGE_INDEX -> R.drawable.member_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_TEAM_PAGE_INDEX -> getString(R.string.my_team_title)
            MEMBER_LIST_PAGE_INDEX -> getString(R.string.member_list_title)
            else -> null
        }
    }
}