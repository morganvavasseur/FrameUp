package com.example.amaze

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.frameup.network.EventService

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_list)

        EventService.getEvents { results, error ->

        }
    }
}
