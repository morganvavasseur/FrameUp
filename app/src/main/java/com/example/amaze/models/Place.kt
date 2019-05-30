/*
 * Developed by Yann Malanda on 5/30/19 9:17 PM.
 * Last modified 5/30/19 9:17 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Place : Serializable {
    @SerializedName("formatted_address") lateinit var formattedAddress : String
    @SerializedName("id") lateinit var id : String
    @SerializedName("name") lateinit var name : String
}