/*
 * Developed by Yann Malanda on 6/4/19 11:04 AM.
 * Last modified 6/4/19 11:04 AM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.amaze.R
import com.example.amaze.fragments.EventGuestFragment
import com.example.amaze.fragments.GuestsStateFragment
import com.example.amaze.fragments.HostedEventFragment
import com.example.amaze.fragments.InvitedEventFragment
import com.example.amaze.models.Guest
import com.example.amaze.network.EventResult

private val TAB_TITLES = arrayOf(
    R.string.comming,
    R.string.not_comming,
    R.string.maybe_text

)

class GuestsStateFragmentAdapter(private val context: Context, fm: FragmentManager, private val event: EventResult) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position) {
            0 -> fragment = EventGuestFragment.newInstance(event.guestsComming as ArrayList<Guest>)
            1 -> fragment = EventGuestFragment.newInstance(event.guestsNotComming as ArrayList<Guest>)
            2 -> fragment = EventGuestFragment.newInstance(event.guestsMaybe as ArrayList<Guest>)
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }

}