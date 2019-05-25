package com.example.amaze.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.amaze.AmazeApp
import com.example.amaze.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        welcome_cl.setOnClickListener({firststeps()})

        var countDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
//                Log.d("tick", millisUntilFinished.toString())
            }

            override fun onFinish() {
                firststeps()
            }
        }.start()
    }

    fun firststeps() {
        var intent = Intent(AmazeApp.sharedInstance, FirstStepsActivity::class.java)
        startActivity(intent)
    }

//    Handler().postDelayed({
//        val intent = Intent(AmazeApp.sharedInstance, FirstStepsActivity::class.java)
//        startActivity(intent)
//        finish()
//    }, 5000)
}
