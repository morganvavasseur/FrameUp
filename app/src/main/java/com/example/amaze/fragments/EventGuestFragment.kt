/*
 * Developed by Yann Malanda on 6/4/19 11:10 AM.
 * Last modified 6/4/19 11:10 AM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.amaze.R
import com.example.amaze.adapters.EventCardAdapter
import com.example.amaze.adapters.InvitedGuestAdapter
import com.example.amaze.adapters.SearchedFriendCardAdapter
import com.example.amaze.models.Guest
import kotlinx.android.synthetic.main.fragment_event_guest.*
import kotlinx.android.synthetic.main.fragment_hosted_event.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EventGuestFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EventGuestFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EventGuestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var guestList: ArrayList<Guest>? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            guestList = it.getSerializable(ARG_PARAM1) as ArrayList<Guest>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_guest, container, false)
    }

    override fun onStart() {
        super.onStart()
        eventGuestRecyclerView.layoutManager = LinearLayoutManager(context!!)
        eventGuestRecyclerView.adapter = InvitedGuestAdapter(guestList as List<Guest>)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
         * @return A new instance of fragment EventGuestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(guestList: ArrayList<Guest>) =
            EventGuestFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, guestList)
                }
            }
    }
}
