package com.example.amaze.activities


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.AmazeApp

import com.example.amaze.R
import com.example.amaze.adapters.ProfilePagerAdapter
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.fragment_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()
        val adapter = ProfilePagerAdapter(AmazeApp.sharedInstance, childFragmentManager)

        profile_viewPager.adapter = adapter

        profile_tabLayout.setupWithViewPager(profile_viewPager)

        var user = SecureStorageServices.authUser
//        imageViewProfilePicture = user.profilePicture
        textViewProfileName?.text = user?.firstName + " " + user?.lastName
    }


}
