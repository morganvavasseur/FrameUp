package com.example.amaze.activities

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.R
import com.example.amaze.adapters.HorizontalFriendListAdapter
import com.example.amaze.adapters.SearchedFriendCardAdapter
import com.example.amaze.models.Event
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.UserResult
import kotlinx.android.synthetic.main.activity_add_guest_to_event.*
import kotlinx.android.synthetic.main.amaze_long_button.view.*
import kotlinx.android.synthetic.main.component_searched_friend_card.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGuestToEventActivity : AppCompatActivity(), SearchedFriendCardAdapter.OnFriendItemListener{

    var event : EventResult? = null
    lateinit var searchedUsername : String // Stocke le username recherché
    var usersResults : ArrayList<UserResult> = ArrayList<UserResult>()
    var guests: ArrayList<UserResult> = ArrayList<UserResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest_to_event)
        event = intent.extras.getSerializable(CreateEventActivity.EVENT_CODE) as? EventResult


        friendsRecyclerView.layoutManager = LinearLayoutManager(this)
        guestsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        friendsRecyclerView.adapter = SearchedFriendCardAdapter(usersResults, guests,this)
        guestsRecyclerView.adapter = HorizontalFriendListAdapter(guests)

        searchFriendButton.amazeLongButton.setOnClickListener({onSearchButtonClick()})
        createEventButton.setOnClickListener({onCreateEventButtonClick()})

        event?.guests = ArrayList<String>()

    }

    fun onSearchButtonClick(){
        searchedUsername = friendsSearchbar.text.toString() // Get searched username from textedit
        val searchUserRequest = RetrofitClient.userService.searchByUsername(searchedUsername, RetrofitClient.PUBLIC_ROLE_ID, RetrofitClient.AUTHENTICATED_ROLE_ID)

        searchUserRequest.enqueue(object : Callback<ArrayList<UserResult>> {
            override fun onFailure(call: Call<ArrayList<UserResult>>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<ArrayList<UserResult>>, response: Response<ArrayList<UserResult>>) {
                var result = response.body()
                if (result is ArrayList<UserResult>) {
                    usersResults = result

                    friendsRecyclerView.layoutManager = LinearLayoutManager(this@AddGuestToEventActivity)
                    friendsRecyclerView.adapter = SearchedFriendCardAdapter(usersResults, guests, this@AddGuestToEventActivity)
                }
            }
        })

    }

    override fun onFriendClick(user: UserResult, userItem: View) {
        Log.d("Listener", "OnFriendClick Clicked")
        val boldTypeface = ResourcesCompat.getFont(AmazeApp.sharedInstance, R.font.open_sans_bold)
        val defaultTypeface = ResourcesCompat.getFont(AmazeApp.sharedInstance, R.font.open_sans_light)

        if (guests.any{ x -> x.id == user.id }){
            userItem.searchFriendName.typeface = defaultTypeface
            guests = guests.filter { it.id != user.id } as ArrayList<UserResult>
        }
        else{
            guests.add(user)
            userItem.searchFriendName.typeface = boldTypeface

        }
        guestsRecyclerView.adapter = HorizontalFriendListAdapter(guests)
    }

    // Met à jour les invités à l'event
    fun putGuestsToEvent(){
        val guestsIds = ArrayList<String>()
        guests.forEach {
            guestsIds.add(it.id)
        }
        event?.guests = guestsIds // Affecte le tagbleau des guests à l'event
    }


    fun onCreateEventButtonClick(){
        putGuestsToEvent()
        val createEventRequest = RetrofitClient.eventService.createEvent(event as EventResult)

        createEventRequest.enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                Toast.makeText(this@AddGuestToEventActivity, response.message(), Toast.LENGTH_LONG)
            }
        })
    }

}
