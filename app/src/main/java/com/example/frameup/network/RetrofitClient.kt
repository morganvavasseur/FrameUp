package com.example.frameup.network

import com.chamber.java.library.SharedChamber
import com.example.frameup.interfaces.EventService
import com.example.frameup.interfaces.UserService
import com.example.frameup.utils.SecureStorageServices
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


// Création de l'instance Retrofit
class RetrofitClient {


    companion object {

        // URL to API
        val BASE_URL : String = "http://10.93.182.81:1337/"


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