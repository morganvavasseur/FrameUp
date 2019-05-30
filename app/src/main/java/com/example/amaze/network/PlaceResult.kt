/*
 * Developed by Yann Malanda on 5/30/19 9:23 PM.
 * Last modified 5/30/19 9:23 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.network

import com.example.amaze.models.Place
import com.google.gson.annotations.SerializedName

class PlaceResult {
    @SerializedName("candidates") lateinit var candidates : ArrayList<Place>
    @SerializedName("status") lateinit var status : String
}