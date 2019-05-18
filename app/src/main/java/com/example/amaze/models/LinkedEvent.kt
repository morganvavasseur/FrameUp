package com.example.amaze.models

import android.location.Location
import com.google.gson.annotations.SerializedName

data class LinkedEvent (

    @SerializedName("_id") val id : String,
    @SerializedName("title") val title : String,
    @SerializedName("date") val date : String,
    @SerializedName("dateIsFinal") val dateIsFinal : Boolean,
    @SerializedName("location") val location : Location,
    @SerializedName("locationIsFinal") val locationIsFinal : Boolean,
    @SerializedName("entrancePrice") val entrancePrice : Int,
    @SerializedName("organizers") val organizers : List<String>,
    @SerializedName("guests") val guests : List<String>,
    @SerializedName("description") val description : String,
    @SerializedName("note") val note : String,
    @SerializedName("guests_comming") val guests_comming : List<String>,
    @SerializedName("guests_not_comming") val guests_not_comming : List<String>,
    @SerializedName("guests_maybe") val guests_maybe : List<String>,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("updatedAt") val updatedAt : String,
    @SerializedName("guestsComming") val guestsComming : List<String>,
    @SerializedName("guestsMaybe") val guestsMaybe : List<String>,
    @SerializedName("guestsNotComming") val guestsNotComming : List<String>
)