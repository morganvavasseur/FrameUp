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