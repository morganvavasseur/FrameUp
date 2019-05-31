/*
 * Developed by Yann Malanda on 5/30/19 3:20 PM.
 * Last modified 5/30/19 3:20 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity

import com.example.amaze.R
import com.example.amaze.adapters.HorizontalFriendListAdapter
import com.example.amaze.adapters.SearchedFriendCardAdapter
import com.example.amaze.components.AmazeNextButton
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.SearchedGuest
import com.example.amaze.network.SendableEvent
import kotlinx.android.synthetic.main.amaze_long_button.view.*
import kotlinx.android.synthetic.main.component_searched_friend_card.view.*
import kotlinx.android.synthetic.main.fragment_add_friends_to_event.*
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
 * [AddFriendsToEventFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddFriendsToEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddFriendsToEventFragment : Fragment(), SearchedFriendCardAdapter.OnFriendItemListener, AmazeNextButton.OnNextButtonListener {
    // TODO: Rename and change types of parameters
    lateinit var event: SendableEvent
    private var listener: OnAddFriendsFragmentListener? = null
    var usersResults : ArrayList<SearchedGuest> = ArrayList()
    var guests: ArrayList<SearchedGuest> = ArrayList()
    lateinit var searchedUsername : String // Stocke le username recherché



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
        return inflater.inflate(R.layout.fragment_add_friends_to_event, container, false)
    }

    override fun onStart() {
        super.onStart()
        friendsRecyclerView.layoutManager = LinearLayoutManager(context)
        guestsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        friendsRecyclerView.adapter = SearchedFriendCardAdapter(usersResults, guests,this)
        guestsRecyclerView.adapter = HorizontalFriendListAdapter(guests)

        searchFriendButton.amazeLongButton.setOnClickListener({onSearchButtonClick()})
        createEventButton.setNextButtonOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAddFriendsFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun onSearchButtonClick(){
        searchedUsername = friendsSearchbar.text.toString() // Get searched username from textedit
        val searchUserRequest = RetrofitClient.userService.searchByUsername(searchedUsername, RetrofitClient.PUBLIC_ROLE_ID, RetrofitClient.AUTHENTICATED_ROLE_ID)

        searchUserRequest.enqueue(object : Callback<ArrayList<SearchedGuest>> {
            override fun onFailure(call: Call<ArrayList<SearchedGuest>>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<ArrayList<SearchedGuest>>, response: Response<ArrayList<SearchedGuest>>) {
                var result = response.body()
                if (result is ArrayList<SearchedGuest>) {
                    usersResults = result

                    friendsRecyclerView.layoutManager = LinearLayoutManager(context)
                    friendsRecyclerView.adapter = SearchedFriendCardAdapter(usersResults, guests, this@AddFriendsToEventFragment)
                }
            }
        })

    }

    override fun onFriendClick(user: SearchedGuest, userItem: View) {
        Log.d("Listener", "OnFriendClick Clicked")
        val boldTypeface = ResourcesCompat.getFont(AmazeApp.sharedInstance, R.font.open_sans_bold)
        val defaultTypeface = ResourcesCompat.getFont(AmazeApp.sharedInstance, R.font.open_sans_light)

        if (guests.any{ x -> x.id == user.id }){
            userItem.searchFriendName.typeface = defaultTypeface
            guests = guests.filter { it.id != user.id } as ArrayList<SearchedGuest>
        }
        else{
            guests.add(user)
            userItem.searchFriendName.typeface = boldTypeface

        }
        guestsRecyclerView.adapter = HorizontalFriendListAdapter(guests)
    }

    fun setOnAddFriendsFragmentListener(callback: OnAddFriendsFragmentListener) {
        this.listener = callback
    }

    override fun onNextButtonClick(){
        putGuestsToEvent()
        listener?.onEventCreated(event)
    }

    // Met à jour les invités à l'event
    fun putGuestsToEvent(){
        val guestsIds = ArrayList<String>()
        guests.forEach {
            guestsIds.add(it.id)
        }
        event?.guests = guestsIds // Affecte le tagbleau des guests à l'event
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
    interface OnAddFriendsFragmentListener {
        // TODO: Update argument type and name
        fun onEventCreated(event: SendableEvent)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFriendsToEventFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: SendableEvent) =
            AddFriendsToEventFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}
