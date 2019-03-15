package com.example.amaze

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.amaze.network.EventService
import com.example.amaze.activities.LoginActivity

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
