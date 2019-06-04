/*
 * Developed by Yann Malanda on 6/4/19 10:08 AM.
 * Last modified 6/4/19 10:08 AM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.AmazeApp

import com.example.amaze.R
import com.example.amaze.adapters.GuestsStateFragmentAdapter
import com.example.amaze.adapters.HomeFragmentAdapter
import com.example.amaze.models.Guest
import com.example.amaze.network.EventResult
import kotlinx.android.synthetic.main.fragment_guests_state.*
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GuestsStateFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GuestsStateFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class GuestsStateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var event: EventResult? = null
    private var commingGuests: List<Guest>? = null
    private var notCommingGuests: List<Guest>? = null
    private var maybeCommingGuests: List<Guest>? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            event = it.getSerializable(ARG_PARAM1) as EventResult
            commingGuests = event?.guestsComming
            notCommingGuests = event?.guestsNotComming
            maybeCommingGuests = event?.guestsMaybe
        }
    }

    override fun onStart() {
        super.onStart()
        val guestsStateFragmentAdapter = GuestsStateFragmentAdapter(AmazeApp.sharedInstance, childFragmentManager, event!!)
        val viewPager : ViewPager = guestsStateViewPager
        viewPager.adapter = guestsStateFragmentAdapter
        guestsStateTabLayout.setupWithViewPager(guestsStateViewPager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guests_state, container, false)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GuestsStateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(event : EventResult) =
            GuestsStateFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, event)

                }
            }
    }
}
