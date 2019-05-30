/*
 * Developed by Yann Malanda on 5/30/19 4:29 PM.
 * Last modified 5/30/19 4:29 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.content.Context
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.amaze.R
import com.example.amaze.activities.CreateEventActivity
import com.example.amaze.models.Place
import com.example.amaze.network.EventResult
import com.example.amaze.network.PlaceResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.SendableEvent
import kotlinx.android.synthetic.main.fragment_places.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlacesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlacesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlacesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var event: SendableEvent? = null
    private var listener: OnPlacesFragmentListener? = null
    lateinit var places: ArrayList<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            event = it.getSerializable(ARG_PARAM1) as SendableEvent
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_places, container, false)
    }

    override fun onStart() {
        super.onStart()
        searchLocationButton.setOnClickListener{searchPlaces()}
    }

    fun searchPlaces() {
        val searchedPlace = locationSearchBar.text.toString()

        val createEventRequest = RetrofitClient.locationService.searchPlace(RetrofitClient.GOOGLE_MAPS_PLACE_API_KEY, searchedPlace)

        createEventRequest.enqueue(object : Callback<PlaceResult> {
            override fun onFailure(call: Call<PlaceResult>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<PlaceResult>, response: Response<PlaceResult>) {
                var result = response.body()?.candidates
                if (result is ArrayList<Place>)
                    places = result

            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPlacesFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
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
    interface OnPlacesFragmentListener {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlacesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(event: SendableEvent) =
            PlacesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, event)
                }
            }
    }
}
