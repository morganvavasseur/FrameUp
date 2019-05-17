package com.example.amaze.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity
import com.example.amaze.R
import com.example.amaze.adapters.LoginSignUpPageAdapater
import com.example.amaze.models.ConnectResults
import com.example.amaze.models.User
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.SupportFunctions.Companion.updateUserInfos
import com.example.amaze.network.UserResult
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginSignUpActivity : FragmentActivity() {

    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_sign_up)

        // Si le token à bien été stocké on connecte l'utilisateur à l'activité principale
        if (SecureStorageServices.authJwtToken != null) {

            // On met à jour les données de l'utilisateur
            updateUserInfos()

            // On démarre l'activité principale
            val intent = Intent(AmazeApp.sharedInstance, MainActivity::class.java)
            startActivity(intent)
        }

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.loginSignUpViewPager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = LoginSignUpPageAdapater(supportFragmentManager)
        mPager.adapter = pagerAdapter



    }


}
