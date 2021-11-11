package com.itdev.teammanager.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.itdev.teammanager.ui.MemberListFragment
import com.itdev.teammanager.ui.TeamFragment
import java.lang.IndexOutOfBoundsException

const val MY_TEAM_PAGE_INDEX = 0
const val MEMBER_LIST_PAGE_INDEX = 1

class TeamManagerPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_TEAM_PAGE_INDEX to {TeamFragment() },
        MEMBER_LIST_PAGE_INDEX to { MemberListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}