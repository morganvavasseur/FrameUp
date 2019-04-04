package com.example.amaze.interfaces

import com.example.amaze.models.Event
import com.example.amaze.utils.SecureStorageServices
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EventService {

    // Récupère les events auquelles un user est connecté en fonction de son id
    @GET("events")
    fun getInvitedEvent(
        @Query("guests_in", encoded = true) userId : String,
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<ArrayList<Event>>

}