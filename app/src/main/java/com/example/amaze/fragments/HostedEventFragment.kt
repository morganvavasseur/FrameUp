/*
 * Developed by Yann Malanda on 5/26/19 3:29 PM.
 * Last modified 5/25/19 10:28 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.AmazeApp

import com.example.amaze.R
import com.example.amaze.activities.CreateEventActivity
import com.example.amaze.activities.EventActivity
import com.example.amaze.adapters.EventCardAdapter
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.utils.ExtraStrings
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.fragment_created_event.*
import kotlinx.android.synthetic.main.fragment_hosted_event.*
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
 * [HostedEventFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HostedEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class HostedEventFragment : Fragment(), EventCardAdapter.OnEventCardListener {

    var events: ArrayList<EventResult> = ArrayList()
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
        return inflater.inflate(R.layout.fragment_hosted_event, container, false)
    }

    override fun onStart() {
        super.onStart()
        val getEventRequest = RetrofitClient.eventService.getHostedEvent(SecureStorageServices.authUser!!.id)

        getEventRequest.enqueue(object : Callback<ArrayList<EventResult>> {
            override fun onFailure(call: Call<ArrayList<EventResult>>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<ArrayList<EventResult>>, response: Response<ArrayList<EventResult>>) {
                var responseEvents = response.body()
                if(responseEvents is ArrayList<EventResult>) {
                    if (!responseEvents.isEmpty())
                        noEventTv.visibility = View.GONE
                    events = responseEvents
                    recyclerViewHostedEvents.layoutManager = LinearLayoutManager(context!!)
                    recyclerViewHostedEvents.adapter = EventCardAdapter(events, this@HostedEventFragment, false)
                }
            }
        })
    }

    override fun onEventCardClick(event: EventResult) {
        val intent = Intent(AmazeApp.sharedInstance, EventActivity::class.java)
        intent.putExtra(ExtraStrings.EXTRA_EVENT, event)
        intent.putExtra(ExtraStrings.EXTRA_IS_OWNER, true)
        startActivity(intent)
    }

    fun onCreateEventButtonClick() {
        var intent = Intent(AmazeApp.sharedInstance, CreateEventActivity::class.java)
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
         * @return A new instance of fragment HostedEventFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HostedEventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
