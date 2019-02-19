package com.example.amaze

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.frameup.activities.LoginActivity
import com.example.frameup.network.EventService

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_list)

        EventService.getEvents { results, error ->

        }

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
