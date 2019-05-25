/*
 * Developed by Yann Malanda on 5/25/19 10:30 PM.
 * Last modified 5/25/19 10:30 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.models

import com.google.gson.annotations.SerializedName

open class AbstractEvent() {
    @SerializedName("title")
    lateinit var title: String
    @SerializedName("date")
    lateinit var date: String
    @SerializedName("dateIsFinal")
    var dateIsFinal: Boolean = false
    @SerializedName("location")
    lateinit var location: Any
    @SerializedName("locationIsFinal")
    var locationIsFinal: Boolean = false
    @SerializedName("entrancePrice")
    var entrancePrice: Number = 0
    @SerializedName("description")
    lateinit var description: String
    @SerializedName("_id")
    lateinit var id: String

}