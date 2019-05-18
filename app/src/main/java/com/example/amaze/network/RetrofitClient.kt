package com.example.amaze.network

import com.example.amaze.interfaces.EventService
import com.example.amaze.interfaces.UserService
import com.example.amaze.models.User
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Création de l'instance Retrofit
class RetrofitClient {


    companion object {

        // URL to API
        val BASE_URL : String = "https://amaze-api.herokuapp.com/"
//        val BASE_URL : String = "http://192.168.1.56:1337/"
        val PUBLIC_ROLE_ID = "5c39cc2fa2442510c3236beb"
        val AUTHENTICATED_ROLE_ID = "5c39cc2fa2442510c3236bea"


        // Retrofit instance declaration
        val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        // Service declaration
        val userService = retrofit.create(UserService::class.java)
        val eventService = retrofit.create(EventService::class.java)

    }
}