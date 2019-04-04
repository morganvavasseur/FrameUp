package com.example.frameup.network

import com.example.frameup.interfaces.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Création de l'instance Retrofit
class RetrofitClient {


    companion object {

        val BASE_URL : String = "http://10.93.182.81:1337/"

        val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val userService = retrofit.create(UserService::class.java)



    }



    /*fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit

    }*/


}