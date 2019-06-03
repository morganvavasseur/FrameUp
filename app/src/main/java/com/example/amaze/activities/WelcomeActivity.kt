package com.example.amaze.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity
import com.example.amaze.R
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        welcome_cl.setOnClickListener({firststeps()})

        var countDownTimer = object : CountDownTimer(1500, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                if (this@WelcomeActivity.hasWindowFocus()) {
                    firststeps()
                }
            }
        }.start()

        if (SecureStorageServices.authJwtToken != null){
            val intent = Intent(AmazeApp.sharedInstance, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    fun firststeps() {
        var intent = Intent(AmazeApp.sharedInstance, FirstStepsActivity::class.java)
        startActivity(intent)
    }
}
