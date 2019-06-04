/*
 * Developed by Yann Malanda on 04/06/19 10:13.
 * Last modified 04/06/19 10:13
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity

import com.example.amaze.R
import kotlinx.android.synthetic.main.fragment_event_successfully_created.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EventSuccessfullyCreatedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_successfully_created, container, false)
    }

    override fun onStart() {
        super.onStart()
        event_successfully_created_button.setOnClickListener({backhome()})
    }

    fun backhome() {
        activity?.finish()
        var intent = Intent(AmazeApp.sharedInstance, MainActivity::class.java)
        startActivity(intent)
    }
}
