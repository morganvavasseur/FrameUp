/*
 * Developed by Yann Malanda on 5/30/19 5:07 PM.
 * Last modified 5/30/19 5:07 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.interfaces

import android.location.Location
import com.example.amaze.models.Place
import com.example.amaze.network.EventResult
import com.example.amaze.network.PlaceResult
import com.example.amaze.utils.SecureStorageServices
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LocationService {

    // Récupère les events auquelles un user est connecté en fonction de son id
    @GET("place/textsearch/json")
    fun searchPlace(
        @Query("key", encoded = true) key : String,
        @Query("query", encoded = true) searchedPlace : String) : Call<PlaceResult>
}