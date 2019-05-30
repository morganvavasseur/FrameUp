package com.example.amaze.network

import com.example.amaze.interfaces.EventService
import com.example.amaze.interfaces.LocationService
import com.example.amaze.interfaces.UserService
import com.google.gson.Gson
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
        val GOOGLE_MAPS_PLACE_API_KEY = "AIzaSyDVn93iHDU1MEpgfFZEzfTmeOeh_NSMyec"
        val GOOGLE_MAPS_API_BASE_URL = "https://maps.googleapis.com/maps/api/"


        // Retrofit instance declaration
        val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val placeRetrofit = Retrofit.Builder()
            .baseUrl(GOOGLE_MAPS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Service declaration
        val userService = retrofit.create(UserService::class.java)
        val eventService = retrofit.create(EventService::class.java)
        val locationService = placeRetrofit.create(LocationService::class.java)


    }
}