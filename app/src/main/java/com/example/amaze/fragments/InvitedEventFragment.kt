/*
 * Developed by Yann Malanda on 5/26/19 3:28 PM.
 * Last modified 5/26/19 3:23 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getDrawable
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.amaze.AmazeApp

import com.example.amaze.R
import com.example.amaze.activities.CreateEventActivity
import com.example.amaze.activities.EventActivity
import com.example.amaze.adapters.EventCardAdapter
import com.example.amaze.components.AmazeEventStateButton
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.utils.ExtraStrings
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.component_event_card.view.*
import kotlinx.android.synthetic.main.fragment_created_event.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, onFoundedPlaceItemListener1.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InvitedEventFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InvitedEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InvitedEventFragment : Fragment(), EventCardAdapter.OnEventCardListener {

    var events: ArrayList<EventResult> = ArrayList()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_created_event, container, false)
    }

    override fun onStart() {
        super.onStart()
        recyclerViewEvents.layoutManager = LinearLayoutManager(context)
        recyclerViewEvents.adapter = EventCardAdapter(events, this, true)

        val getEventRequest = RetrofitClient.eventService.getInvitedEvent(SecureStorageServices.authUser!!.id)

        getEventRequest.enqueue(object : Callback<ArrayList<EventResult>> {
            override fun onFailure(call: Call<ArrayList<EventResult>>, t: Throwable) {
                noInvitedEventTv.setText(R.string.error_load_event)
            }

            override fun onResponse(call: Call<ArrayList<EventResult>>, response: Response<ArrayList<EventResult>>) {
                var responseEvents = response.body()
                if(responseEvents is ArrayList<EventResult>) {
                    if (responseEvents.isNotEmpty()){
                        noInvitedEventIv.visibility = View.GONE
                        noInvitedEventTv.visibility = View.GONE

                    }
                    events = responseEvents
                    recyclerViewEvents.layoutManager = LinearLayoutManager(context)
                    recyclerViewEvents.adapter = EventCardAdapter(events, this@InvitedEventFragment, true)
                }
            }
        })
    }


    override fun onEventCardClick(event: EventResult) {
        val intent = Intent(AmazeApp.sharedInstance, EventActivity::class.java)
        intent.putExtra(ExtraStrings.EXTRA_EVENT, event)
        intent.putExtra(ExtraStrings.EXTRA_IS_OWNER, false)
        startActivity(intent)
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
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InvitedEventFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InvitedEventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
