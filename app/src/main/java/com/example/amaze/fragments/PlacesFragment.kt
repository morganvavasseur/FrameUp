/*
 * Developed by Yann Malanda on 5/30/19 4:29 PM.
 * Last modified 5/30/19 4:29 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.amaze.R
import com.example.amaze.adapters.FoundedPlacesItemAdapter
import com.example.amaze.models.Place
import com.example.amaze.network.PlaceResult
import com.example.amaze.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_places.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, onFoundedPlaceItemListener1.g. ARG_ITEM_NUMBER
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
class PlacesFragment : Fragment(), TextWatcher{


    // TODO: Rename and change types of parameters
    private var listener: OnPlacesFragmentListener? = null
    var places: ArrayList<Place> = ArrayList()
    lateinit var onFoundedPlaceItemListener1 : FoundedPlacesItemAdapter.OnFoundedPlaceItemListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        locationSearchBar.addTextChangeListener(this)
        placesRecyclerView.layoutManager = LinearLayoutManager(context)
        placesRecyclerView.adapter = FoundedPlacesItemAdapter(places, onFoundedPlaceItemListener1)

    }

    fun searchPlaces() {
        val searchedPlace = locationSearchBar.text

        val createEventRequest = RetrofitClient.locationService.searchPlace(RetrofitClient.GOOGLE_MAPS_PLACE_API_KEY, searchedPlace)

        createEventRequest.enqueue(object : Callback<PlaceResult> {
            override fun onFailure(call: Call<PlaceResult>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<PlaceResult>, response: Response<PlaceResult>) {
                var result = response.body()?.results
                if (result is ArrayList<Place>) {
                    places = result
                    placesRecyclerView.layoutManager = LinearLayoutManager(context)
                    placesRecyclerView.adapter = FoundedPlacesItemAdapter(places, onFoundedPlaceItemListener1)
                }
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



    override fun afterTextChanged(s: Editable?) {
        searchPlaces()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }



    fun setOnPlaceFragmentListener(callback: OnPlacesFragmentListener){
        listener = callback
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
        fun onPlaceSelected(selectedPlace: Place)
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
        fun newInstance(onFoundedPlaceItemListener: FoundedPlacesItemAdapter.OnFoundedPlaceItemListener) =
            PlacesFragment().apply {
                arguments = Bundle().apply {
                    onFoundedPlaceItemListener1 = onFoundedPlaceItemListener
                }
            }
    }
}
