package com.example.amaze.interfaces

import com.example.amaze.models.Event
import com.example.amaze.network.EventResult
import com.example.amaze.network.SendableEvent
import com.example.amaze.utils.SecureStorageServices
import retrofit2.Call
import retrofit2.http.*

interface EventService {

    // Récupère les events auquelles un user est connecté en fonction de son id
    @GET("events")
    fun getInvitedEvent(
        @Query("guests_in", encoded = true) userId : String,
@Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<ArrayList<EventResult>>

    // Récupère les events auquelles un user est connecté en fonction de son id
    @GET("events")
    fun getHostedEvent(
        @Query("organizers_in", encoded = true) userId : String,
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<ArrayList<EventResult>>

    // Créer une soirée
    @POST("events")
    fun createEvent(
        @Body event : SendableEvent,
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<EventResult>

    @PUT("events/{id}")
    fun updateEvent(
        @Path("id") id : String,
        @Body event : SendableEvent,
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<EventResult>

    // Récupère les events auquelles un user est connecté en fonction de son id
    @GET("events/{id}")
    fun getEvent(
        @Path("id") id : String,
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<EventResult>

    // Supprime un event
    @DELETE("events/{id}")
    fun deleteEvent(
        @Path("id") id : String,
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<Any>
}