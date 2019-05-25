/*
 * Developed by Yann Malanda on 5/25/19 4:24 PM.
 * Last modified 5/19/19 5:51 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.amaze.R
import com.example.amaze.fragments.InvitedEventFragment
import com.example.amaze.fragments.HostedEventFragment

private val TAB_TITLES = arrayOf(
    R.string.invited,
    R.string.hosted
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class HomeFragmentAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position) {
            0 -> fragment = InvitedEventFragment.newInstance("","")
            1 -> fragment = HostedEventFragment.newInstance("", "")
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}