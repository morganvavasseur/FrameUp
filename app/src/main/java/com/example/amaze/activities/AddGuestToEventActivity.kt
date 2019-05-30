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

class AddGuestToEventActivity : AppCompatActivity(){

    var event : SendableEvent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest_to_event)
        event = intent.extras.getSerializable(CreateEventActivity.EVENT_CODE) as? SendableEvent

        event?.guests = ArrayList<String>()

    }


}
