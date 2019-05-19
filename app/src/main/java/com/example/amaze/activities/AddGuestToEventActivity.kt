package com.example.amaze.activities

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity
import com.example.amaze.R
import com.example.amaze.adapters.HorizontalFriendListAdapter
import com.example.amaze.adapters.SearchedFriendCardAdapter
import com.example.amaze.components.AmazeNextButton
import com.example.amaze.models.Event
import com.example.amaze.models.Guest
import com.example.amaze.models.User
import com.example.amaze.network.*
import kotlinx.android.synthetic.main.activity_add_guest_to_event.*
import kotlinx.android.synthetic.main.amaze_long_button.view.*
import kotlinx.android.synthetic.main.component_searched_friend_card.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGuestToEventActivity : AppCompatActivity(), SearchedFriendCardAdapter.OnFriendItemListener, AmazeNextButton.OnNextButtonListener{

    var event : SendableEvent? = null
    lateinit var searchedUsername : String // Stocke le username recherché
    var usersResults : ArrayList<SearchedGuest> = ArrayList()
    var guests: ArrayList<SearchedGuest> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest_to_event)
        event = intent.extras.getSerializable(CreateEventActivity.EVENT_CODE) as? SendableEvent


        friendsRecyclerView.layoutManager = LinearLayoutManager(this)
        guestsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        friendsRecyclerView.adapter = SearchedFriendCardAdapter(usersResults, guests,this)
        guestsRecyclerView.adapter = HorizontalFriendListAdapter(guests)

        searchFriendButton.amazeLongButton.setOnClickListener({onSearchButtonClick()})
        createEventButton.setNextButtonOnClickListener(this)

        event?.guests = ArrayList<String>()

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

                    friendsRecyclerView.layoutManager = LinearLayoutManager(this@AddGuestToEventActivity)
                    friendsRecyclerView.adapter = SearchedFriendCardAdapter(usersResults, guests, this@AddGuestToEventActivity)
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

    // Met à jour les invités à l'event
    fun putGuestsToEvent(){
        val guestsIds = ArrayList<String>()
        guests.forEach {
            guestsIds.add(it.id)
        }
        event?.guests = guestsIds // Affecte le tagbleau des guests à l'event
    }


    override fun onNextButtonClick(){
        putGuestsToEvent()
        val createEventRequest = RetrofitClient.eventService.createEvent(event!!)

        createEventRequest.enqueue(object : Callback<EventResult> {
            override fun onFailure(call: Call<EventResult>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<EventResult>, response: Response<EventResult>) {
                val intent = Intent(AmazeApp.sharedInstance, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }

}
