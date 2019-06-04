/*
 * Developed by Yann Malanda on 6/4/19 8:32 PM.
 * Last modified 6/4/19 7:42 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import com.example.amaze.AmazeApp
import com.example.amaze.activities.CreateEventActivity
import com.example.amaze.adapters.HomeFragmentAdapter
import kotlinx.android.synthetic.main.fragment_home.*


// the fragment initialization parameters, onFoundedPlaceItemListener1.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        val homeFragmentAdapter = HomeFragmentAdapter(AmazeApp.sharedInstance, childFragmentManager)
        val viewPager : ViewPager = homeViewPager
        viewPager.adapter = homeFragmentAdapter
        homeTabs.setupWithViewPager(homeViewPager)
        createEventButton.setOnClickListener({onCreateEventButtonClick()})

    }

    fun onCreateEventButtonClick() {
        var intent = Intent(AmazeApp.sharedInstance, CreateEventActivity::class.java)
        startActivity(intent)
    }


}
