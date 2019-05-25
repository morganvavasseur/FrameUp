/*
 * Developed by Yann Malanda on 5/25/19 4:24 PM.
 * Last modified 4/25/19 11:09 AM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.amaze.fragments.LoginFragment
import com.example.amaze.fragments.SignUpFragment

class LoginSignUpPageAdapater(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    val nbFragments = 2

    override fun getItem(pos: Int): Fragment {
        when (pos) {

            0 -> return LoginFragment()
            1 -> return SignUpFragment()

            else -> return LoginFragment()
        }
    }

    override fun getCount(): Int = nbFragments


}